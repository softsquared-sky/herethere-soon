package com.example.herethereproject.src.main.userModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainUserPictureResponse {

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

        @SerializedName("postPicture")
        private String postPicture;

        public String getPostPicture() {
            return postPicture;
        }
    }
}
