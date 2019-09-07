package com.example.herethereproject.src.main.userInterfaces;

import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainUserRetrofitInterface {


    @GET("/user/{email}/profile")
    Call<MainUserProfileResponse> getUserProfile(@Path("email") String email);

    @GET("/user/{email}/posts")
    Call<MainPostsResponse> getUserPosts(@Path("email") String email, @Query("current") int current);

    @GET("/user/{email}/posts/pictures?current")
    Call<MainUserPictureResponse> getUserPicture(@Path("email") String email, @Query("current") int current);
}
