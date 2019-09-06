package com.example.herethereproject.src.mainHome;

import com.example.herethereproject.src.mainHome.postsInterfaces.MainPostsRetrofitInterface;
import com.example.herethereproject.src.mainHome.postsModels.MainHeartBody;
import com.example.herethereproject.src.mainHome.postsModels.MainHeartResponse;
import com.example.herethereproject.src.mainHome.postsModels.MainPostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class MainHomeService {
    private final MainHomeFragment mMainActivityPostsView;

    MainHomeService(final MainHomeFragment mainActivityPostsView) {
        this.mMainActivityPostsView = mainActivityPostsView;
    }

    void getPosts(int postNo) {
        final MainPostsRetrofitInterface mainPostsRetrofitInterface = getRetrofit().create(MainPostsRetrofitInterface.class);
        mainPostsRetrofitInterface.getPosts(0).enqueue(new Callback<MainPostsResponse>() {
            @Override
            public void onResponse(Call<MainPostsResponse> call, Response<MainPostsResponse> response) {
                final MainPostsResponse mainPostsResponse = response.body();
                if (mainPostsResponse == null) {
                    mMainActivityPostsView.validateFailure("null");
                    return;
                }
                mMainActivityPostsView.validateSuccess(mainPostsResponse.getMessage(), mainPostsResponse.getResult(), mainPostsResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<MainPostsResponse> call, Throwable t) {
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