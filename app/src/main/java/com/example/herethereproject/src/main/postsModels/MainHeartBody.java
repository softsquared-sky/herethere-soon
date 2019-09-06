package com.example.herethereproject.src.main.postsModels;

import com.google.gson.annotations.SerializedName;

public class MainHeartBody {

    @SerializedName("postNo")
    public int postNo;


    public MainHeartBody(int postNo){
        this.postNo = postNo;
    }
}
