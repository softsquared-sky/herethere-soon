package com.example.herethereproject.src.login.interfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;

public interface LoginRetrofitInterface {

    Call<SignUpRegionResponse> postJwt(@Body RequestBody params);
}
