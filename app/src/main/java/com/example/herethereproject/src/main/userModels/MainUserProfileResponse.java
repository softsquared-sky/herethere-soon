package com.example.herethereproject.src.main.userModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainUserProfileResponse {

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

        @SerializedName("email")
        private String email;

        @SerializedName("schoolName")
        private String schoolName;

        @SerializedName("status")
        private String status;

        public String getEmail() {
            return email;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public String getStatus() {
            return status;
        }

        public String getUserPicture() {
            return userPicture;
        }

        public String getNickName() {
            return nickName;
        }
    }
}
