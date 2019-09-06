package com.example.herethereproject.src.login.loginModels;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private Data result;

    public class Data{
        @SerializedName("jwt")
        private String jwt;
        public String getJwt(){
            return this.jwt;
        }
    }



    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public Data getResult() {
        return result;
    }


}
