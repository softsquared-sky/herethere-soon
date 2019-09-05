package com.example.herethereproject.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
/*
    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    public LoginResponse(String email, String password){
        this.email = email;
        this.password = password;
    }
*/
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private Data result;

    public Data getResult() {
        return result;
    }

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


}
