package com.example.herethereproject.src.signUp.SignUpRegion.regionModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SignUpRegionBody {
    public int reqType;
    public String email;
    public String passowrd;
    public String name;
    public int birth;
    public String nickName;
    public String schoolPicture;
    public String schoolName;
    public List<Integer> locationNo;
    public SignUpRegionBody(int reqType, String email, String password, String name, int birth, String nickName, String schoolPicture, String schoolName, List<Integer> locationNo){
        this.reqType = reqType;
        this.email = email;
        this.passowrd = password;
        this.name = name;
        this.birth = birth;
        this.nickName = nickName;
        this.schoolPicture = schoolPicture;
        this.schoolName = schoolName;
        this.locationNo = locationNo;
    }

}