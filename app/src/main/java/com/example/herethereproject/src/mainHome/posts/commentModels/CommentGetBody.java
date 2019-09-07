package com.example.herethereproject.src.mainHome.posts.commentModels;

import com.google.gson.annotations.SerializedName;

public class CommentGetBody {

    @SerializedName("postNo")
    public int postNo;


    public CommentGetBody(int postNo){
        this.postNo = postNo;
    }
}
