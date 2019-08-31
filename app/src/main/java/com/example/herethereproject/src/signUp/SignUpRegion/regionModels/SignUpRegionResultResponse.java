package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionResultResponse {

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
