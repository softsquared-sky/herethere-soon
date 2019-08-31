package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionResponse {

    public ArrayList<SignUpRegionResultResponse> results = new ArrayList<SignUpRegionResultResponse>();

    public ArrayList<SignUpRegionResultResponse> getResult(){
        return results;
    }

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public class result{
        @SerializedName("locationNo")
        private int locationNo;

        @SerializedName("location")
        private String location;

        public int getLocationNo(){
            return locationNo;
        }

        public String getLocation(){
            return location;
        }
    }

}
