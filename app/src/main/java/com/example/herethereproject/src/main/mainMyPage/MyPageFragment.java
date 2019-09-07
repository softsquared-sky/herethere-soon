package com.example.herethereproject.src.main.mainMyPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.herethereproject.R;
import com.example.herethereproject.src.main.MainService;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import java.util.List;

public class MyPageFragment extends Fragment implements MainActivityUserView {

    private FragmentManager mManager;
    private String mPageEmail;

    private Fragment mPostsFragment, mPictureFragment;

    private int mFragmentCheck = 0;

    private Button mPostButton;
    private Button mPictureButton;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_mypage, container,false);

        if(getArguments() != null){
            String mPageEmail = getArguments().getString("email"); // 전달한 key 값
        }

        tryUserProfileGet(mPageEmail);

        mManager = getActivity().getSupportFragmentManager();
        mPostsFragment = new MyPagePostsFragment();
        mManager.beginTransaction().replace(R.id.frame_mypage, mPostsFragment).commit();

        mPostButton = view.findViewById(R.id.btn_mypage_contents);
        mPictureButton = view.findViewById(R.id.btn_mypage_picture);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_mypage_contents:
                        mPostButton.setTextColor(ContextCompat.getColor(view.getContext(),R.color.hereThereText));
                        mPictureButton.setTextColor(ContextCompat.getColor(view.getContext(),R.color.mainHomeProfileStatus));
                        mFragmentCheck = 0;
                        switchFragment();
                        break;

                    case R.id.btn_mypage_picture:
                        mPictureButton.setTextColor(ContextCompat.getColor(view.getContext(),R.color.hereThereText));
                        mPostButton.setTextColor(ContextCompat.getColor(view.getContext(),R.color.mainHomeProfileStatus));
                        mFragmentCheck = 1;
                        switchFragment();
                        break;
                }
            }
        };

        mPostButton.setOnClickListener(listener);
        mPictureButton.setOnClickListener(listener);



        return view;


    }



    public void switchFragment(){
        switch (mFragmentCheck){
            case 0:
                if(mPostsFragment == null){
                    mPostsFragment = new MyPagePostsFragment();
                    mManager.beginTransaction().add(R.id.frame_mypage, mPostsFragment).commit();
                }


                if(mPostsFragment != null)
                    mManager.beginTransaction().show(mPostsFragment).commit();
                if(mPictureFragment != null)
                    mManager.beginTransaction().hide(mPictureFragment).commit();

                break;

            case 1:
                if(mPictureFragment == null){
                    mPictureFragment = new MyPagePictureFragment();
                    mManager.beginTransaction().add(R.id.main_frame, mPictureFragment).commit();
                }



                if(mPostsFragment != null)
                    mManager.beginTransaction().hide(mPostsFragment).commit();
                if(mPictureFragment != null)
                    mManager.beginTransaction().show(mPictureFragment).commit();
                break;
        }


    }





    public void tryUserProfileGet(String email){
        final MainService mainService = new MainService(this);
        mainService.getUserProfile(email);
    }

    @Override
    public void validateUserProfileSuccess(MainUserProfileResponse.Result result) {
        TextView myPageNickNameTextView = getView().findViewById(R.id.tv_mypage_nickName);
        TextView myPageEmailTextView = getView().findViewById(R.id.tv_mypage_email);
        TextView myPageSchoolTextView = getView().findViewById(R.id.tv_mypage_school);
        TextView myPageStatusTextView = getView().findViewById(R.id.tv_mypage_status);

        myPageNickNameTextView.setText(result.getNickName());
        myPageEmailTextView.setText(result.getEmail());
        myPageSchoolTextView.setText(result.getSchoolName());
        myPageStatusTextView.setText(result.getStatus());
    }

    @Override
    public void validateUserpostsSuccess(String message, List<MainPostsResponse.Data> result, int code) {

    }

    @Override
    public void validateUserPictureSuccess(String message, List<MainUserPictureResponse.Result> result, int code) {

    }

    @Override
    public void validateUserProfileFailure(String message) {

    }
}
