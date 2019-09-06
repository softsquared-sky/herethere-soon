package com.example.herethereproject.src.signUp.signUpModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpNickNameBody {
    @Expose
    @SerializedName("reqType")
    public int reqType;

    @Expose
    @SerializedName("nickName")
    public String nickName;



    public SignUpNickNameBody(int reqType, String nickName){
        this.reqType = reqType;
        this.nickName = nickName;
    }

}