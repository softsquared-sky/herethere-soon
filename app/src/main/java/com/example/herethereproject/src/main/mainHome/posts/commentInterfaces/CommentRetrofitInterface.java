package com.example.herethereproject.src.main.mainHome.posts.commentInterfaces;

import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentGetResponse;
import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentPostBody;
import com.example.herethereproject.src.main.mainHome.posts.commentModels.CommentPostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentRetrofitInterface {


    @GET("/comment")
    Call<CommentGetResponse> getComment(@Query("current") int current, @Query("postNo") int postNo);

    @POST("/comment")
    Call<CommentPostResponse> postComment(@Body CommentPostBody commentPostBody);
}
