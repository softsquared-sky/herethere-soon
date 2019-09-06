package com.example.herethereproject.src.main;

import com.example.herethereproject.src.main.userInterfaces.MainActivityUserView;
import com.example.herethereproject.src.main.userInterfaces.MainUserRetrofitInterface;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class MainService {
    private final MainActivityUserView mMainActivityUserView;

    MainService(final MainActivityUserView signUpActivityView) {
        this.mMainActivityUserView = signUpActivityView;
    }


    void getUserProfile(String email){
        final MainUserRetrofitInterface mainUserRetrofitInterface = getRetrofit().create(MainUserRetrofitInterface.class);



        mainUserRetrofitInterface.getUserProfile(email).enqueue(new Callback<MainUserProfileResponse>() {
            @Override
            public void onResponse(Call<MainUserProfileResponse> call, Response<MainUserProfileResponse> response) {
                final MainUserProfileResponse mainUserProfileResponse = response.body();
                if (mainUserProfileResponse == null) {
                    mMainActivityUserView.validateUserProfileFailure("null");
                    return;
                }
                if(mainUserProfileResponse.getIsSuccess()){
                    mMainActivityUserView.validateUserProfileSuccess(mainUserProfileResponse.getResult().get(0));
                }
            }

            @Override
            public void onFailure(Call<MainUserProfileResponse> call, Throwable t) {
                mMainActivityUserView.validateUserProfileFailure("fail");
            }
        });
    }
}