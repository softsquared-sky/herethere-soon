package com.example.herethereproject.src.signUp.SignUpModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpEmailBody {
    @Expose
    @SerializedName("reqType")
    public int reqType;

    @Expose
    @SerializedName("email")
    public String email;


    public SignUpEmailBody(int reqType, String email) {
        this.reqType = reqType;
        this.email = email;
    }
}