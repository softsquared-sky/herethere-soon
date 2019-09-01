package com.example.herethereproject.src.login;

import com.example.herethereproject.src.login.interfaces.LoginActivityView;
import com.example.herethereproject.src.login.interfaces.LoginRetrofitInterface;
import com.example.herethereproject.src.login.models.LoginBody;
import com.example.herethereproject.src.login.models.LoginResponse;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionActivityView;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionRetrofitInterface;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postJwt() {
        System.out.println("inpost");
        LoginResponse loginResponse = new LoginResponse();
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postJwt("randu2345@naver.com", "q1w2e3").enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(null);
                    return;
                }

                mLoginActivityView.validateSuccess("success");
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure("fail");
            }
        });
    }
}