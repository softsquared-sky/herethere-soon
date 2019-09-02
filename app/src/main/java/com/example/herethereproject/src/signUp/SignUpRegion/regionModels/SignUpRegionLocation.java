package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SignUpRegionLocation {
    @Expose
    @SerializedName("locationNo")
    public int locationNo;

    public SignUpRegionLocation(int locationNo){

        this.locationNo = locationNo;
    }

}