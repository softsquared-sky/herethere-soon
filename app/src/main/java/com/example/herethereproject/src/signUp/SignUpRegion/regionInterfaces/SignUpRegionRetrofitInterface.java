package com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionBody;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpRegionRetrofitInterface {

    @GET("/location")
    Call<SignUpRegionResponse> getTest();

    @POST("/user")
    Call<SignUpRegionResponse> postUser(@Body SignUpRegionBody signUpRegionBody);

}
