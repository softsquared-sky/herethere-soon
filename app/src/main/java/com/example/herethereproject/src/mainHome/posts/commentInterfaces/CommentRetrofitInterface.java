package com.example.herethereproject.src.mainHome.posts.commentInterfaces;

import com.example.herethereproject.src.mainHome.posts.commentModels.CommentGetBody;
import com.example.herethereproject.src.mainHome.posts.commentModels.CommentGetResponse;
import com.example.herethereproject.src.mainHome.posts.commentModels.CommentPostBody;
import com.example.herethereproject.src.mainHome.posts.commentModels.CommentPostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommentRetrofitInterface {


    @HTTP(method = "GET", path = "/comment", hasBody = true)
    Call<CommentGetResponse> getComment(@Body CommentGetBody commentGetBody, @Query("current") int current);

    @POST("/comment")
    Call<CommentPostResponse> postComment(@Body CommentPostBody commentPostBody);
}
