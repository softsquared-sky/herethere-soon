package com.example.herethereproject.src.signUp.SignUpRegion;

import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionActivityView;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionRetrofitInterface;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionLocation;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


class SignUpRegionService {
    private final SignUpRegionActivityView mSignUpRegionActivityView;

    SignUpRegionService(final SignUpRegionActivityView signUpRegionActivityView) {
        this.mSignUpRegionActivityView = signUpRegionActivityView;
    }

    void getLocation() {
        final SignUpRegionRetrofitInterface signUpRegionRetrofitInterface = getRetrofit().create(SignUpRegionRetrofitInterface.class);
        signUpRegionRetrofitInterface.getTest().enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpRegionActivityView.validateFailure(null);
                    return;
                }
                List<SignUpRegionResponse.data> result = signUpRegionResponse.getResult();
                //System.out.println(result.get(0).getLocation());
                mSignUpRegionActivityView.validateSuccessGet(result);
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpRegionActivityView.validateFailure("fail");
            }
        });
    }

    void postUser(){
        final SignUpRegionRetrofitInterface signUpRegionRetrofitInterface = getRetrofit().create(SignUpRegionRetrofitInterface.class);
        SignUpRegionLocation signUpRegionLocation = new SignUpRegionLocation(1);
        List<SignUpRegionLocation> locationNo = new ArrayList<SignUpRegionLocation>();
        locationNo.add(signUpRegionLocation);
        //System.out.println(locationNo.get(0).locationNo);
        //final SignUpRegionBody signUpRegionBody = new SignUpRegionBody(2, "randy3456@naver.com", "q1w2e3", "q1w2e3", "홍순재", 960603, "hsj321", "asdf.jpg", "항공대", integerList);
        signUpRegionRetrofitInterface.postUser(2, "lemon1234@naver.com", "lemon1234", "lemon1234", "이재혁", 960603, "lemon", "http://naver.com/lemon.jpg", "항공대학교", locationNo).enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpRegionActivityView.validateFailure(null);
                    return;
                }
                mSignUpRegionActivityView.validateSuccessPost(signUpRegionResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpRegionActivityView.validateFailure("fail");
            }
        });
    }
}
