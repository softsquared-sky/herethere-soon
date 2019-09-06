package com.example.herethereproject.src.signUp.signUpInterfaces;

import com.example.herethereproject.src.signUp.signUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;

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
