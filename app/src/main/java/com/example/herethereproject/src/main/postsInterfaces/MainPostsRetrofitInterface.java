package com.example.herethereproject.src.main.postsInterfaces;

import com.example.herethereproject.src.main.postsModels.MainHeartBody;
import com.example.herethereproject.src.main.postsModels.MainHeartResponse;
import com.example.herethereproject.src.main.postsModels.MainPostsBody;
import com.example.herethereproject.src.main.postsModels.MainPostsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface MainPostsRetrofitInterface {


    @GET("/posts")
    Call<MainPostsResponse> getPosts(@Query("current") int current);

    @POST("/posts")
    Call<MainPostsResponse> postPosts(@Body MainPostsBody mainPostsBody);

    @GET("/posts/{postNo}")
    Call<MainPostsResponse> getPostsNo(@Part("postNo") int postNo);

    @POST("/heart")
    Call<MainHeartResponse> postHeart(@Body MainHeartBody mainHeartBody);

}
