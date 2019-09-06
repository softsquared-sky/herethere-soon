package com.example.herethereproject.src.signUp.signUpInterfaces;

import com.example.herethereproject.src.signUp.signUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.signUpModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SignUpRetrofitInterface {

    @GET("/location")
    Call<SignUpRegionResponse> getTest();

    @POST("/user")
    Call<SignUpResponse> postUser(@Body SignUpBody signUpBody);

}
