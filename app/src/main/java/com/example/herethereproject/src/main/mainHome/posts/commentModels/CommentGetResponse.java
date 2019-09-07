package com.example.herethereproject.src.main.mainHome.posts.commentModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommentGetResponse {

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

        @SerializedName("timeAgo")
        private String timeAgo;

        @SerializedName("commentContents")
        private String commentContents;

        public String getUserPicture() {
            return userPicture;
        }

        public String getNickName() {
            return nickName;
        }

        public String getTimeAgo() {
            return timeAgo;
        }

        public String getCommentContents() {
            return commentContents;
        }
    }
}
