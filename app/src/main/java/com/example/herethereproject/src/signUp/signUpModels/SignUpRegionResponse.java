package com.example.herethereproject.src.signUp.signUpModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionResponse {
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
    public List<data> result = new ArrayList<>();

    public List<data> getResult(){
        return result;
    }

    public class data {

        @SerializedName("locationNo")
        public int locationNo;

        @SerializedName("location")
        public String location;

        public int getLocationNo(){
            return locationNo;
        }

        public String getLocation(){
            return location;
        }

    }
}