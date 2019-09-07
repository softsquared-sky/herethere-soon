package com.example.herethereproject.src.main.mainMyPage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.main.mainHome.MainHomeAdapter;
import com.example.herethereproject.src.main.mainHome.MainHomeItem;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import java.util.ArrayList;
import java.util.List;

public class MyPagePostsFragment extends Fragment implements MainActivityUserView {

    private ProgressDialog mDialog;

    public ArrayList<MainHomeItem> mData = new ArrayList<>();

    private MainHomeItem mMainHomeItem;

    private int mPostNo = 0;

    private RecyclerView mPostRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_mypage_posts, container, false);
        mPostRecyclerView = rootView.findViewById(R.id.list_mypage_posts);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mPostRecyclerView.setLayoutManager(layoutManager);

        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setIndeterminate(true);
        //질문필요
        tryGetUserPosts("asdf",0);
        return rootView;
    }


    private void tryGetUserPosts(String email, int current){
        mDialog.show();
        final MyPageService myPageService = new MyPageService(this);
        myPageService.getUserPosts(email, current);

    }

    @Override
    public void validateUserProfileSuccess(MainUserProfileResponse.Result result) {
    }

    @Override
    public void validateUserpostsSuccess(String message, List<MainPostsResponse.Data> result, int code) {
        if(code == 102){
            for(MainPostsResponse.Data data : result){

                int postNo = data.getPostNo();
                //System.out.println(postNo);
                String userPicture;
                if(data.getUserPicture() == null){
                    userPicture = "";
                } else{
                    userPicture = data.getUserPicture();
                }

                String nickName = data.getNickName();
                String postLocation = data.getPostLocation();
                String timeAgo = data.getTimeAgo();
                String postContents = data.getPostContents();

                if(data.getPictureList().get(0).getPostPicture() == null){
                    data.getPictureList().get(0).setPostPicture();
                    System.out.println(data.getPictureList().get(0).getPostPicture());
                }
                List<MainPostsResponse.Data.Picture> pictureList = data.getPictureList();
                int heart = data.getHeart();
                String comment = data.getComment();
                mMainHomeItem = new MainHomeItem(postNo,userPicture, nickName, postLocation, timeAgo, postContents,heart, comment, pictureList);
                mData.add(mMainHomeItem);
            }

            MainHomeAdapter homeAdapter = new MainHomeAdapter(mData);
            mPostRecyclerView.setAdapter(homeAdapter);
        } else if(code == 103){
            TextView notTextView = getView().findViewById(R.id.tv_mypage_posts_not);
            notTextView.setText(message);
        }
        System.out.println(message);
        mDialog.hide();

    }

    @Override
    public void validateUserPictureSuccess(String message, List<MainUserPictureResponse.Result> result, int code) {

    }

    @Override
    public void validateUserProfileFailure(String message) {
        System.out.println(message);
        mDialog.hide();

    }
}
