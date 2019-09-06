package com.example.herethereproject.src.mainHome.posts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsResponse;

import java.util.List;

public class PostsActivity extends BaseActivity implements MainActivityPostsView {

    boolean mHeartCheck = false;

    private TextView mHeartTextView;
    private ImageView mHeartImageView;
    private int mPostNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        mHeartTextView = findViewById(R.id.tv_posts_heart);
        mHeartImageView = findViewById(R.id.iv_posts_heart);

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
        tryPostsNo(mPostNo);



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

            case R.id.btn_posts_back:
                finish();
                break;
        }
    }

    public void tryPostHeart(int postNo){
        final PostsService postsService = new PostsService(this);
        postsService.postHeart(postNo);
    }


    public void tryPostsNo(int postNo){
        final PostsService postsService = new PostsService(this);
        postsService.getPostsNo(postNo);
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
        } else {
            System.out.println(isSuccess);
        }
        hideProgressDialog();
    }

    @Override
    public void validateSuccessHeart(boolean isSccess) {
        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        System.out.println(message);
        hideProgressDialog();
    }
}
