package com.example.herethereproject.src.login;

import com.example.herethereproject.src.login.interfaces.LoginActivityView;
import com.example.herethereproject.src.login.interfaces.LoginRetrofitInterface;
import com.example.herethereproject.src.login.models.LoginBody;
import com.example.herethereproject.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final LoginActivityView mLoginActivityView;

    LoginService(final LoginActivityView loginActivityView) {
        this.mLoginActivityView = loginActivityView;
    }

    void postJwt(String email, String password) {
        //LoginBody loginBody = new LoginBody("lemon34@naver.com", "s123444");
        LoginBody loginBody = new LoginBody(email, password);
        LoginResponse loginResponse = new LoginResponse();
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.postJwt(loginBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                final LoginResponse loginResponse = response.body();
                if (loginResponse == null) {
                    mLoginActivityView.validateFailure(null);
                    return;
                }
                mLoginActivityView.validateSuccess(loginResponse.getMessage(), loginResponse.getIsSuccess());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure("fail");
            }
        });
    }
}