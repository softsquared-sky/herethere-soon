package com.example.herethereproject.src.signUp.signUpModels;

import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
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

}