package com.example.herethereproject.src.main.mainHome.postsModels;

import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MainPostsBody {

    @SerializedName("result")
    public List<SignUpRegionResponse.data> result = new ArrayList<>();

    public List<SignUpRegionResponse.data> getResult(){
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
