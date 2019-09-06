package com.example.herethereproject.src.login.loginInterfaces;

import com.example.herethereproject.src.login.loginModels.LoginBody;
import com.example.herethereproject.src.login.loginModels.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginRetrofitInterface {

    /*
    @FormUrlEncoded
    @POST("/jwt")
    Call<LoginResponse> postJwt(@Field("email") String email,
                                @Field("password") String password);

                                */

    @POST("/jwt")
    Call<LoginResponse> postJwt(@Body LoginBody loginBody);
}
