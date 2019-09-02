package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionBody {
    @Expose
    public int reqType;

    @Expose
    public String email;

    @Expose
    public String password;

    @Expose
    public String rePasswrod;

    @Expose
    public String name;

    @Expose
    public int birth;

    @Expose
    public String nickName;

    @Expose
    public String schoolPicture;

    @Expose
    public String schoolName;

    @Expose
    public List<LocationList> locationList;


    public SignUpRegionBody(int reqType, String email, String password, String rePasswrod, String name, int birth, String nickName, String schoolPicture, String schoolName, List<LocationList> locationList){
        this.reqType = reqType;
        this.email = email;
        this.password = password;
        this.rePasswrod = rePasswrod;
        this.name = name;
        this.birth = birth;
        this.nickName = nickName;
        this.schoolPicture = schoolPicture;
        this.schoolName = schoolName;
        this.locationList = locationList;
    }

    public static class LocationList{
        @Expose
        public int locationNo;

        public LocationList(int locationNo){
            this.locationNo = locationNo;
        }
    }
}