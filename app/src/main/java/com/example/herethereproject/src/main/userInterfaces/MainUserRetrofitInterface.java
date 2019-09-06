package com.example.herethereproject.src.main.userInterfaces;

import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MainUserRetrofitInterface {


    @GET("/user/{email}/profile")
    Call<MainUserProfileResponse> getUserProfile(@Path("email") String email);


}
