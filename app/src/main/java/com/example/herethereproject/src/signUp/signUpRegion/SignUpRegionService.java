package com.example.herethereproject.src.signUp.signUpRegion;

import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpActivityView;
import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpRetrofitInterface;
import com.example.herethereproject.src.signUp.signUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.signUpModels.SignUpResponse;

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



        final SignUpBody signUpBody = new SignUpBody(2, email, password, password,name, birth,nickName,schoolPicture, schoolName, locationList);

        System.out.println(signUpBody.email);
        System.out.println(signUpBody.password);
        System.out.println(signUpBody.name);
        System.out.println(signUpBody.birth);
        System.out.println(signUpBody.nickName);
        System.out.println(signUpBody.schoolName);
        System.out.println(signUpBody.schoolPicture);


        signUpRetrofitInterface.postUser(signUpBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.validateFailure("null");
                    return;
                }
                mSignUpActivityView.validateSuccessPost(signUpResponse.getIsSuccess(), signUpResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("fail");
            }
        });
    }
}
