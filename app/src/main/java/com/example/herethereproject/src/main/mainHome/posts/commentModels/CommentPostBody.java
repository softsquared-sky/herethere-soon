package com.example.herethereproject.src.main.mainHome.posts.commentModels;

import com.google.gson.annotations.SerializedName;

public class CommentPostBody {

    @SerializedName("postNo")
    public int postNo;

    @SerializedName("commentContents")
    public String commentContents;


    public CommentPostBody(int postNo, String commentContents){
        this.postNo = postNo;
        this.commentContents = commentContents;
    }
}
