package com.example.herethereproject.src.signUp.SignUpPrivacy;

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


class SignUpPrivacyService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpPrivacyService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }


    void postUser(int reqType, String nickName){
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);

        final SignUpBody signUpBody = new SignUpBody(reqType, null, null,null, null, null, nickName, null, null, null);


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
