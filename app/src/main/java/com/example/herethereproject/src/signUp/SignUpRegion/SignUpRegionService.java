package com.example.herethereproject.src.signUp.SignUpRegion;

import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionActivityView;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionRetrofitInterface;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static  com.example.herethereproject.src.ApplicationClass.getRetrofit;

class SignUpRegionService {
    private final SignUpRegionActivityView mSignUpRegionActivityView;

    SignUpRegionService(final SignUpRegionActivityView signUpRegionActivityView){
        this.mSignUpRegionActivityView = signUpRegionActivityView;
    }

    void getLocation(){
        final SignUpRegionRetrofitInterface signUpRegionRetrofitInterface = getRetrofit().create(SignUpRegionRetrofitInterface.class);
        signUpRegionRetrofitInterface.getLocation().enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if(signUpRegionResponse == null) {
                    mSignUpRegionActivityView.validateFailure(null);
                    return;
                }
                mSignUpRegionActivityView.validateSuccess(signUpRegionResponse.getResult());
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpRegionActivityView.validateFailure(null);
            }
        });
    }


}
