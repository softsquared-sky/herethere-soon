package com.example.herethereproject.src.signUp.SignUpRegion;

import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionActivityView;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionRetrofitInterface;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionBody;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionLocation;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpResponse;


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

    void postUser(String email, String password, String name, int birth, String nickName, String schoolPicture, String schoolName, List<SignUpRegionBody.LocationList> locationList){
        final SignUpRegionRetrofitInterface signUpRegionRetrofitInterface = getRetrofit().create(SignUpRegionRetrofitInterface.class);

        SignUpRegionBody.LocationList signUpRegionLocation = new SignUpRegionBody.LocationList(1);
        List<SignUpRegionBody.LocationList> locationNo = new ArrayList<SignUpRegionBody.LocationList>();
        locationNo.add(signUpRegionLocation);

        //String password = "q1w2e3";
        final SignUpRegionBody signUpRegionBody = new SignUpRegionBody(2, email, password, password, name, birth, nickName, schoolPicture, schoolName, locationNo);

        //final SignUpRegionBody signUpRegionBody = new SignUpRegionBody(2, "randy3456@naver.com", "q1w2e3","q1w2e3", "홍순재", 960603, "hsj321", "http://naver.com/lemon.jpg", "항공대학교", locationNo);

        System.out.println(signUpRegionBody.email);
        System.out.println(signUpRegionBody.password);
        System.out.println(signUpRegionBody.name);
        System.out.println(signUpRegionBody.birth);
        System.out.println(signUpRegionBody.nickName);
        System.out.println(signUpRegionBody.schoolName);
        System.out.println(signUpRegionBody.schoolPicture);
//        System.out.println(signUpRegionBody.locationList.get(1).locationNo);


        signUpRegionRetrofitInterface.postUser(signUpRegionBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpRegionResponse = response.body();
                if (signUpRegionResponse == null) {
                    mSignUpRegionActivityView.validateFailure("null");
                    return;
                }
                mSignUpRegionActivityView.validateSuccessPost(signUpRegionResponse.getIsSuccess(), signUpRegionResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpRegionActivityView.validateFailure("fail");
            }
        });
    }
}
