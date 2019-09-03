package com.example.herethereproject.src.signUp.SignUpRegion;

import com.example.herethereproject.src.signUp.SignUpInterfaces.SignUpActivityView;
import com.example.herethereproject.src.signUp.SignUpInterfaces.SignUpRetrofitInterface;

import com.example.herethereproject.src.signUp.SignUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.SignUpModels.SignUpRegionResponse;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


class SignUpRegionService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpRegionService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }

    void getLocation() {
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);
        signUpRetrofitInterface.getTest().enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpActivityView.validateFailure(null);
                    return;
                }
                List<SignUpRegionResponse.data> result = signUpRegionResponse.getResult();
                //System.out.println(result.get(0).getLocation());
                mSignUpActivityView.validateSuccessGet(result);
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("fail");
            }
        });
    }

    void postUser(String email, String password, String name, int birth, String nickName, String schoolPicture, String schoolName, List<SignUpBody.LocationList> locationList){
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);

        SignUpBody.LocationList signUpRegionLocation = new SignUpBody.LocationList(1);
        List<SignUpBody.LocationList> locationNo = new ArrayList<SignUpBody.LocationList>();
        locationNo.add(signUpRegionLocation);

        //String password = "q1w2e3";

        final SignUpBody signUpBody = new SignUpBody(2, "randy3456@naver.com", "q1w2e3","q1w2e3", "홍순재", 960603, "hsj321", "http://naver.com/lemon.jpg", "항공대학교", locationNo);

        System.out.println(signUpBody.email);
        System.out.println(signUpBody.password);
        System.out.println(signUpBody.name);
        System.out.println(signUpBody.birth);
        System.out.println(signUpBody.nickName);
        System.out.println(signUpBody.schoolName);
        System.out.println(signUpBody.schoolPicture);


        signUpRetrofitInterface.postUser(signUpBody).enqueue(new Callback<SignUpRegionResponse>() {
            @Override
            public void onResponse(Call<SignUpRegionResponse> call, Response<SignUpRegionResponse> response) {
                final SignUpRegionResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpActivityView.validateFailure("null");
                    return;
                }
                mSignUpActivityView.validateSuccessPost(signUpRegionResponse.getIsSuccess(), signUpRegionResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpRegionResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("fail");
            }
        });
    }
}
