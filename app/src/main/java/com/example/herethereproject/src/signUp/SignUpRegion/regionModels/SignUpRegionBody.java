package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionBody {
    @Expose
    @SerializedName("reqType")
    public int reqType;

    @Expose
    @SerializedName("email")
    public String email;

    @Expose
    @SerializedName("password")
    public String password;

    @Expose
    @SerializedName("rePassword")
    public String rePassword;

    @Expose
    @SerializedName("name")
    public String name;

    @Expose
    @SerializedName("birth")
    public int birth;

    @Expose
    @SerializedName("nickName")
    public String nickName;

    @Expose
    @SerializedName("schoolPicture")
    public String schoolPicture;

    @Expose
    @SerializedName("schoolName")
    public String schoolName;

    @Expose
    @SerializedName("locationList")
    public List<LocationList> locationList;


    public SignUpRegionBody(int reqType, String email, String password, String rePassword, String name, int birth, String nickName, String schoolPicture, String schoolName, List<LocationList> locationList){
        this.reqType = reqType;
        this.email = email;
        this.password = password;
        this.rePassword = rePassword;
        this.name = name;
        this.birth = birth;
        this.nickName = nickName;
        this.schoolPicture = schoolPicture;
        this.schoolName = schoolName;
        this.locationList = locationList;
    }

    public static class LocationList{
        @Expose
        @SerializedName("locationNo")
        public int locationNo;

        public LocationList(int locationNo){
            this.locationNo = locationNo;
        }
    }
}