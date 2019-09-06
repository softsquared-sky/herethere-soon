package com.example.herethereproject.src.mainHome.postsModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainPostsNoResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

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
    private List<Result> result = new ArrayList<>();

    public List<Result> getResult(){
        return result;
    }

    public class Result {

        @SerializedName("userPicture")
        private String userPicture;

        @SerializedName("nickName")
        private String nickName;

        @SerializedName("postLocation")
        private String postLocation;

        @SerializedName("timeAgo")
        private String timeAgo;

        @SerializedName("postContents")
        private String postContents;

        @SerializedName("pictureList")
        private List<postPicture> pictureList = new ArrayList<>();

        public List<postPicture> getPictureList(){
            return pictureList;
        }

        public class postPicture{
            @SerializedName("postPicture")
            private String postPicture;


            public void setPostPicture(){
                postPicture = "";
            }


            public String getPostPicture() {
                return this.postPicture;
            }
        }

        @SerializedName("heart")
        private int heart;

        @SerializedName("comment")
        private String comment;

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
