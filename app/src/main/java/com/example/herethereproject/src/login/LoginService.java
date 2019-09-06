package com.example.herethereproject.src.login;

import android.content.SharedPreferences;

import com.example.herethereproject.src.ApplicationClass;
import com.example.herethereproject.src.login.loginInterfaces.LoginActivityView;
import com.example.herethereproject.src.login.loginInterfaces.LoginRetrofitInterface;
import com.example.herethereproject.src.login.loginModels.LoginBody;
import com.example.herethereproject.src.login.loginModels.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.herethereproject.src.ApplicationClass.getRetrofit;
import static com.example.herethereproject.src.ApplicationClass.sSharedPreferences;

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
                if(loginResponse.getIsSuccess()){
                    ApplicationClass.X_ACCESS_TOKEN = loginResponse.getResult().getJwt();
                    SharedPreferences.Editor editor = sSharedPreferences.edit();
                    String jwt = loginResponse.getResult().getJwt(); // 사용자가 입력한 저장할 데이터
                    editor.putString(X_ACCESS_TOKEN,jwt);
                    editor.commit();
                }



            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mLoginActivityView.validateFailure("fail");
                System.out.println(t.getCause());
            }
        });
    }
}