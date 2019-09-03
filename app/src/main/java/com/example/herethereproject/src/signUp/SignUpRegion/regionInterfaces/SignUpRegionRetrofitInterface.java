package com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionBody;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionLocation;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpResponse;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpRegionRetrofitInterface {

    @GET("/location")
    Call<SignUpRegionResponse> getTest();

    @POST("/user")
    Call<SignUpResponse> postUser(@Body SignUpRegionBody signUpRegionBody);

/*
    @FormUrlEncoded
    @POST("/user")
    Call<SignUpRegionResponse> postUser(@Field("reqType") int reqType,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("rePassword") String rePassword,
                                        @Field("name") String name,
                                        @Field("birth") int birth,
                                        @Field("nickName") String nickName,
                                        @Field("schoolPicture") String schoolPicture,
                                        @Field("schoolName") String schoolName,
                                        @Field("locationList") List<SignUpRegionLocation> locationList);
                                        */


}
