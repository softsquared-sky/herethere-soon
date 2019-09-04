package com.example.herethereproject.src.main.postsModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainPostsResponse {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("isSuccess")
    public boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }


    @SerializedName("result")
    public List<MainPostsResponse.data> result = new ArrayList<>();

    public List<MainPostsResponse.data> getResult(){
        return result;
    }

    public class data {

        @SerializedName("postNo")
        public int postNo;

        @SerializedName("userPicture")
        public String userPicture;

        @SerializedName("nickName")
        public String nickName;

        @SerializedName("postLocation")
        public String postLocation;

        @SerializedName("timeAgo")
        public String timeAgo;

        @SerializedName("postContents")
        public String postContents;

        @SerializedName("pictureList")
        public String pictureList;

        public class picture{
            @SerializedName("postPicture")
            public String postPicture;
        }

        @SerializedName("heart")
        public int heart;

        @SerializedName("comment")
        public String comment;

        public int getPostNo(){
            return postNo;
        }

        public String getUserPicture() {
            return userPicture;
        }

        public String getNickName() {
            return nickName;
        }

        public String getPostLocation() {
            return postLocation;
        }

        public String getTimeAgo() {
            return timeAgo;
        }

        public String getPostContents() {
            return postContents;
        }

        public int getHeart() {
            return heart;
        }

        public String getComment() {
            return comment;
        }
    }
}
