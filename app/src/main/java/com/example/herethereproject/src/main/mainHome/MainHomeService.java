package com.example.herethereproject.src.main.mainHome;

import com.example.herethereproject.src.main.postsInterfaces.MainActivityPostsView;
import com.example.herethereproject.src.main.postsInterfaces.MainPostsRetrofitInterface;
import com.example.herethereproject.src.main.postsModels.MainPostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class MainHomeService {
    private final MainActivityPostsView mMainActivityPostsView;

    MainHomeService(final MainActivityPostsView mainActivityPostsView) {
        this.mMainActivityPostsView = mainActivityPostsView;
    }

    void getPosts() {
        final MainPostsRetrofitInterface mainPostsRetrofitInterface = getRetrofit().create(MainPostsRetrofitInterface.class);
        mainPostsRetrofitInterface.getPosts(0).enqueue(new Callback<MainPostsResponse>() {
            @Override
            public void onResponse(Call<MainPostsResponse> call, Response<MainPostsResponse> response) {
                final MainPostsResponse mainPostsResponse = response.body();
                if (mainPostsResponse == null) {
                    mMainActivityPostsView.validateFailure(null);
                    return;
                }
                mMainActivityPostsView.validateSuccess("success");
            }

            @Override
            public void onFailure(Call<MainPostsResponse> call, Throwable t) {
                mMainActivityPostsView.validateFailure("fail");
            }
        });
    }
}