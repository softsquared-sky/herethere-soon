package com.example.herethereproject.src.mainHome;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsResponse;

import java.util.ArrayList;
import java.util.List;

public class MainHomeFragment extends Fragment implements MainActivityPostsView {

    private ProgressDialog mDialog;

    public ArrayList<MainHomeItem> mData = new ArrayList<>();

    private MainHomeItem mMainHomeItem;

    private int mPostNo = 0;

    private RecyclerView mHomeRecyclerView;




    public MainHomeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_home, container, false);
        mHomeRecyclerView = rootView.findViewById(R.id.list_home);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mHomeRecyclerView.setLayoutManager(layoutManager);



        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage(getString(R.string.loading));
        mDialog.setIndeterminate(true);
        //질문필요

        tryGetPosts(mPostNo);
        return rootView;
    }


    private void tryGetPosts(int postNo){
        mDialog.show();
        final MainHomeService mainHomeService = new MainHomeService(this);
        mainHomeService.getPosts(postNo);

    }

    @Override
    public void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess) {
        if(isSuccess){
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
            mHomeRecyclerView.setAdapter(homeAdapter);
        }


        mDialog.hide();
    }

    @Override
    public void validateSuccessPostNo(String message, List<MainPostsNoResponse.Result> result, boolean isSuccess) {

    }

    public void tryPostHeart(int postNo){
        final MainHomeService mainHomeService = new MainHomeService(this);
        mainHomeService.postHeart(postNo);
    }

    @Override
    public void validateSuccessHeart(boolean isSccess) {

    }

    @Override
    public void validateFailure(String message) {
        mDialog.hide();
    }
}
