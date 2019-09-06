package com.example.herethereproject.src.mainHome.posts;

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

    PostsService(final MainActivityPostsView mainActivityPostsView) {
        this.mMainActivityPostsView = mainActivityPostsView;
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
}
