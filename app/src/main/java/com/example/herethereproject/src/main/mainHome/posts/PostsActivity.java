package com.example.herethereproject.src.main.mainHome.posts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.main.mainHome.posts.commentInterfaces.CommentActivityView;
import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentGetResponse;
import com.example.herethereproject.src.main.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends BaseActivity implements MainActivityPostsView, CommentActivityView {

    boolean mHeartCheck = false;

    private TextView mHeartTextView;
    private ImageView mHeartImageView;
    private RecyclerView mCommentListView;
    private RecyclerView mPictureListView;


    private int mPostNo;

    private ArrayList<PostsItem> mCommentList;
    private ArrayList<PostsPictureItem> mPictureList;

    private int mCurrent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);



        mHeartTextView = findViewById(R.id.tv_posts_heart);
        mHeartImageView = findViewById(R.id.iv_posts_heart);
        mCommentListView = findViewById(R.id.list_posts_comment);
        mPictureListView = findViewById(R.id.list_posts_picture);

        RecyclerView.LayoutManager commemtLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager pictureLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCommentListView.setLayoutManager(commemtLayoutManager);
        mPictureListView.setLayoutManager(pictureLayoutManager);


        Intent homeIntent = getIntent();
        mPostNo = homeIntent.getIntExtra("postNo",-1);
        mHeartCheck = homeIntent.getBooleanExtra("heartCheck", false);
        if(mHeartCheck){
            mHeartTextView.setTextColor(ContextCompat.getColor(this, R.color.heartColor));
            mHeartImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_fill));
        }
        if(mPostNo == -1){
            finish();
        }
        mCommentList = new ArrayList<>();
        mPictureList = new ArrayList<>();
        tryPostsNo(mPostNo);
        tryGetComment(mCurrent, mPostNo);
    }

    private void setAdapter(){

    }


    public void postsOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_posts_heart:
                if (!mHeartCheck) {
                    mHeartCheck = true;
                    tryPostHeart(mPostNo);
                    int heart = Integer.parseInt(mHeartTextView.getText().toString()) + 1;
                    mHeartTextView.setText(Integer.toString(heart));
                    mHeartTextView.setTextColor(ContextCompat.getColor(this, R.color.heartColor));
                    mHeartImageView.setImageDrawable(getDrawable(R.drawable.ic_heart_fill));
                    mHeartTextView.setEnabled(false);
                    break;
                }
                break;

            case R.id.btn_posts_uplode:
                EditText commentEditText = findViewById(R.id.et_posts_comment);
                if(!commentEditText.getText().toString().isEmpty()){
                    tryPostComment(mPostNo, commentEditText.getText().toString());
                    commentEditText.setText(null);
                    mCommentList.clear();
                    tryGetComment(0, mPostNo);

                }
                break;

            case R.id.btn_posts_back:
                finish();
                break;
        }
    }

    public void tryPostHeart(int postNo){
        final PostsService postsService = new PostsService(this, this);
        postsService.postHeart(postNo);
        showProgressDialog();
    }


    public void tryPostsNo(int postNo){
        final PostsService postsService = new PostsService(this, this);
        postsService.getPostsNo(postNo);
        showProgressDialog();
    }

    public void tryGetComment(int current, int postNo){
        final PostsService postsService = new PostsService(this, this);
        postsService.getComment(current, postNo);
        showProgressDialog();
    }

    public void tryPostComment(int postNo, String comment){
        final  PostsService postsService = new PostsService(this, this);
        postsService.postComment(postNo, comment);
        showProgressDialog();
    }



    @Override
    public void validateSuccess(String message, List<MainPostsResponse.Data> result, boolean isSuccess) {
        hideProgressDialog();

    }

    @Override
    public void validateSuccessPostNo(String message, List<MainPostsNoResponse.Result> result, boolean isSuccess) {
        if(isSuccess){
            MainPostsNoResponse.Result data = result.get(0);

            String regionTime = data.getPostLocation() + "∙" + data.getTimeAgo();

            TextView nickNameTextView = findViewById(R.id.tv_posts_profile_name);
            TextView regionTimeTextView = findViewById(R.id.tv_posts_region);
            TextView postContentsTextView = findViewById(R.id.tv_posts_line);
            TextView commentTextView = findViewById(R.id.tv_posts_comment);

            nickNameTextView.setText(data.getNickName());
            regionTimeTextView.setText(regionTime);
            postContentsTextView.setText(data.getPostContents());
            mHeartTextView.setText(Integer.toString(data.getHeart()));
            commentTextView.setText(data.getComment());

            for(MainPostsNoResponse.Result.postPicture postPicture : data.getPictureList()){
                PostsPictureItem postsPictureItem = new PostsPictureItem(postPicture.getPostPicture());
                mPictureList.add(postsPictureItem);
            }
            System.out.println(mPictureList.size());
            PostsPictureAdapter postsPictureAdapter = new PostsPictureAdapter(mPictureList);
            mPictureListView.setVisibility(View.VISIBLE);
            mPictureListView.setAdapter(postsPictureAdapter);
        } else {
            System.out.println(isSuccess);
        }
        hideProgressDialog();
    }

    @Override
    public void validateSuccessHeart(boolean isSccess) {
        hideProgressDialog();
    }


    // 댓글 api
    @Override
    public void validateSuccessCommentPost(String message, boolean isSuccess) {
        hideProgressDialog();
    }

    @Override
    public void validateSuccessCommentGet(String message, List<CommentGetResponse.Result> result, boolean isSuccess) {
        if(mCommentList.isEmpty()){
            for(CommentGetResponse.Result data : result){
                PostsItem postsItem = new PostsItem(data.getNickName(), data.getCommentContents(), data.getTimeAgo());
                mCommentList.add(postsItem);
            }

            PostsAdapter postsAdapter = new PostsAdapter(mCommentList);
            mCommentListView.setAdapter(postsAdapter);
        }


    }

    @Override
    public void validateFailure(String message) {
        System.out.println(message);
        hideProgressDialog();
    }
}
