package com.example.herethereproject.src.mainHome.posts;

import com.example.herethereproject.src.mainHome.posts.commentInterfaces.CommentActivityView;
import com.example.herethereproject.src.mainHome.posts.commentInterfaces.CommentRetrofitInterface;
import com.example.herethereproject.src.mainHome.posts.commentModels.CommentGetBody;
import com.example.herethereproject.src.mainHome.posts.commentModels.CommentGetResponse;
import com.example.herethereproject.src.mainHome.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.mainHome.postsInterfaces.MainPostsRetrofitInterface;
import com.example.herethereproject.src.mainHome.postsModels.MainHeartBody;
import com.example.herethereproject.src.mainHome.postsModels.MainHeartResponse;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsNoResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


class PostsService {
    private final MainActivityPostsView mMainActivityPostsView;
    private final CommentActivityView mCommentActivityView;

    PostsService(final MainActivityPostsView mainActivityPostsView, final CommentActivityView commentActivityView) {
        this.mMainActivityPostsView = mainActivityPostsView;
        this.mCommentActivityView = commentActivityView;
    }


    void getPostsNo(int postNo){
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
        final CommentGetBody commentGetBody = new CommentGetBody(postNo);

        commentRetrofitInterface.getComment(commentGetBody, current).enqueue(new Callback<CommentGetResponse>() {
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
}
