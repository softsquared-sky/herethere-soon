package com.example.herethereproject.src.signUp.SignUpInterfaces;

import com.example.herethereproject.src.signUp.SignUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.SignUpModels.SignUpRegionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {

    @GET("/location")
    Call<SignUpRegionResponse> getTest();

    @POST("/user")
    Call<SignUpRegionResponse> postUser(@Body SignUpBody signUpBody);

}
