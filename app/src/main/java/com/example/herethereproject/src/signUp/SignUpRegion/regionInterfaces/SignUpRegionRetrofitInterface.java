package com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface SignUpRegionRetrofitInterface {

    @GET("/location")
    Call<SignUpRegionResponse> getLocation();

    Call<SignUpRegionResponse> postUser(@Body RequestBody params);
}
