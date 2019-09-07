package com.example.herethereproject.src.main.mainMyPage;

import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userInterfaces.MainUserRetrofitInterface;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class MyPageService {
    private final MainActivityUserView mMainActivityUserView;

    MyPageService(final MainActivityUserView mainActivityUserView) {
        this.mMainActivityUserView = mainActivityUserView;
    }

    void getUserPosts(String email, int current) {
        final MainUserRetrofitInterface mainUserRetrofitInterface = getRetrofit().create(MainUserRetrofitInterface.class);
        mainUserRetrofitInterface.getUserPosts("randy3456@naver.com", 0).enqueue(new Callback<MainPostsResponse>() {
            @Override
            public void onResponse(Call<MainPostsResponse> call, Response<MainPostsResponse> response) {
                final MainPostsResponse mainPostsResponse = response.body();
                if (mainPostsResponse == null) {
                    mMainActivityUserView.validateUserProfileFailure("null");
                    return;
                }
                mMainActivityUserView.validateUserpostsSuccess(mainPostsResponse.getMessage(), mainPostsResponse.getResult(), mainPostsResponse.getCode());
            }

            @Override
            public void onFailure(Call<MainPostsResponse> call, Throwable t) {
                mMainActivityUserView.validateUserProfileFailure("fail");
            }
        });
    }

    public void getUserPicture(String email, int current){
        final MainUserRetrofitInterface mainUserRetrofitInterface = getRetrofit().create(MainUserRetrofitInterface.class);

        mainUserRetrofitInterface.getUserPicture(email, current).enqueue(new Callback<MainUserPictureResponse>() {
            @Override
            public void onResponse(Call<MainUserPictureResponse> call, Response<MainUserPictureResponse> response) {
                final MainUserPictureResponse mainUserPictureResponse = response.body();
                if (mainUserPictureResponse == null) {
                    mMainActivityUserView.validateUserProfileFailure("null");
                    return;
                }
                if(mainUserPictureResponse.getIsSuccess()){
                    mMainActivityUserView.validateUserPictureSuccess(mainUserPictureResponse.getMessage(), mainUserPictureResponse.getResult(), mainUserPictureResponse.getCode());
                }
            }

            @Override
            public void onFailure(Call<MainUserPictureResponse> call, Throwable t) {
                mMainActivityUserView.validateUserProfileFailure("fail");
            }
        });
    }
}