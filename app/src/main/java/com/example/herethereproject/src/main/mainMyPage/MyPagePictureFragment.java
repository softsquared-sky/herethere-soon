package com.example.herethereproject.src.main.mainMyPage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.main.mainHome.MainHomePictureItem;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import java.util.ArrayList;
import java.util.List;

public class MyPagePictureFragment extends Fragment implements MainActivityUserView {

    private ProgressDialog mDialog;

    public ArrayList<MainHomePictureItem> mPictureList = new ArrayList<>();

    private RecyclerView mPictureRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_mypage_posts, container, false);
        mPictureRecyclerView = rootView.findViewById(R.id.list_mypage_posts);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        mPictureRecyclerView.setLayoutManager(layoutManager);

        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setIndeterminate(true);

        tryGetUserPosts("wogur1598@naver.com",0);


        return rootView;
    }

    private void tryGetUserPosts(String email, int current){
        mDialog.show();
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getUserPicture(email, current);

    }

    @Override
    public void validateUserProfileSuccess(MainUserProfileResponse.Result result) {
        mDialog.hide();

    }

    @Override
    public void validateUserpostsSuccess(String message, List<MainPostsResponse.Data> result, int code) {
        mDialog.hide();

    }

    @Override
    public void validateUserPictureSuccess(String message, List<MainUserPictureResponse.Result> result, int code) {
        if(code == 102){
            for(MainUserPictureResponse.Result pictureList : result){
                MainHomePictureItem mainHomePictureItem = new MainHomePictureItem(pictureList.getPostPicture());
                mPictureList.add(mainHomePictureItem);
            }
            MyPagePictureAdapter adapter = new MyPagePictureAdapter(mPictureList);
            mPictureRecyclerView.setAdapter(adapter);
        }

        mDialog.hide();

    }

    @Override
    public void validateUserProfileFailure(String message) {
        mDialog.hide();

    }
}
