package com.example.herethereproject.src.main.mainHome.postsInterfaces;

import com.example.herethereproject.src.main.mainHome.postsModels.MainHeartBody;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsNoResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainHeartResponse;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsBody;
import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MainPostsRetrofitInterface {


    @GET("/posts")
    Call<MainPostsResponse> getPosts(@Query("current") int current);

    @POST("/posts")
    Call<MainPostsResponse> postPosts(@Body MainPostsBody mainPostsBody);

    @GET("/post/{postNo}")
    Call<MainPostsNoResponse> getPostsNo(@Path("postNo") int postNo);


    @POST("/heart")
    Call<MainHeartResponse> postHeart(@Body MainHeartBody mainHeartBody);

}
