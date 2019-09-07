package com.example.herethereproject.src.main.mainHome.posts;

import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentPostBody;
import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentPostResponse;
import com.example.herethereproject.src.main.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.main.mainHome.postsInterfaces.MainPostsRetrofitInterface;
import com.example.herethereproject.src.main.mainHome.postsModels.MainHeartBody;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.main.mainHome.posts.commentInterfaces.CommentActivityView;
import com.example.herethereproject.src.main.mainHome.posts.commentInterfaces.CommentRetrofitInterface;
import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentGetResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainHeartResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


public class PostsService {
    private final MainActivityPostsView mMainActivityPostsView;
    private final CommentActivityView mCommentActivityView;

    public PostsService(final MainActivityPostsView mainActivityPostsView, final CommentActivityView commentActivityView) {
        this.mMainActivityPostsView = mainActivityPostsView;
        this.mCommentActivityView = commentActivityView;
    }


    public void getPostsNo(int postNo){
        final MainPostsRetrofitInterface mainPostsRetrofitInterface = getRetrofit().create(MainPostsRetrofitInterface.class);

        mainPostsRetrofitInterface.getPostsNo(postNo).enqueue(new Callback<MainPostsNoResponse>() {
            @Override
            public void onResponse(Call<MainPostsNoResponse> call, Response<MainPostsNoResponse> response) {
                final MainPostsNoResponse mainPostsNoResponse = response.body();
                if (mainPostsNoResponse == null) {
                    mMainActivityPostsView.validateFailure("null");
                    return;
                }
                mMainActivityPostsView.validateSuccessPostNo(mainPostsNoResponse.getMessage(), mainPostsNoResponse.getResult(), mainPostsNoResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<MainPostsNoResponse> call, Throwable t) {
                mMainActivityPostsView.validateFailure("fail");
            }
        });
    }

    void postHeart(int postNo){
        final MainPostsRetrofitInterface mainPostsRetrofitInterface = getRetrofit().create(MainPostsRetrofitInterface.class);


        final MainHeartBody mainHeartBody = new MainHeartBody(postNo);


        mainPostsRetrofitInterface.postHeart(mainHeartBody).enqueue(new Callback<MainHeartResponse>() {
            @Override
            public void onResponse(Call<MainHeartResponse> call, Response<MainHeartResponse> response) {
                final MainHeartResponse mainHeartResponse = response.body();
                if (mainHeartResponse == null) {
                    mMainActivityPostsView.validateFailure("null");
                    return;
                }
                mMainActivityPostsView.validateSuccessHeart(mainHeartResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<MainHeartResponse> call, Throwable t) {
                mMainActivityPostsView.validateFailure("fail");
            }
        });
    }

    void getComment(int current, int postNo){
        final CommentRetrofitInterface commentRetrofitInterface = getRetrofit().create(CommentRetrofitInterface.class);

        commentRetrofitInterface.getComment(current, postNo).enqueue(new Callback<CommentGetResponse>() {
            @Override
            public void onResponse(Call<CommentGetResponse> call, Response<CommentGetResponse> response) {
                final CommentGetResponse commentGetResponse = response.body();
                if(commentGetResponse == null) {
                    mCommentActivityView.validateFailure("null");
                    return;
                }
                mCommentActivityView.validateSuccessCommentGet(commentGetResponse.getMessage(), commentGetResponse.getResult(), commentGetResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<CommentGetResponse> call, Throwable t) {
                mCommentActivityView.validateFailure(t.getMessage());
            }
        });
    }

    void postComment(int postNo, String comment){
        final CommentRetrofitInterface commentRetrofitInterface = getRetrofit().create(CommentRetrofitInterface.class);

        final CommentPostBody commentPostBody = new CommentPostBody(postNo, comment);

        commentRetrofitInterface.postComment(commentPostBody).enqueue(new Callback<CommentPostResponse>() {
            @Override
            public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                final CommentPostResponse commentPostResponse = response.body();
                if(commentPostResponse == null) {
                    mCommentActivityView.validateFailure("null");
                    return;
                }
                mCommentActivityView.validateSuccessCommentPost(commentPostResponse.getMessage(), commentPostResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<CommentPostResponse> call, Throwable t) {
                mCommentActivityView.validateFailure(t.getMessage());
            }
        });
    }
}
