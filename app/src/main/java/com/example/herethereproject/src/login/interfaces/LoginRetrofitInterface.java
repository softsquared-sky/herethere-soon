package com.example.herethereproject.src.login.interfaces;

import com.example.herethereproject.src.login.models.LoginBody;
import com.example.herethereproject.src.login.models.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
