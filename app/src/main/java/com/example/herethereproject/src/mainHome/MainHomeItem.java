package com.example.herethereproject.src.mainHome;

import com.example.herethereproject.src.mainHome.postsModels.MainPostsResponse;

import java.util.List;

public class MainHomeItem{


    private int postNo;
    private String userPicture;
    private String nickName;
    private String postLocation;
    private String timeAgo;
    private String postContents;
    private int heart;
    private String comment;
    public List<MainPostsResponse.Data.Picture> pictureList;

    public MainHomeItem(int postNo, String  userPicture, String nickName, String postLocation, String timeAgo, String postContents, int heart, String comment, List<MainPostsResponse.Data.Picture> pictureList){
        this.postNo = postNo;
        this.userPicture = userPicture;
        this.nickName = nickName;
        this.postLocation = postLocation;
        this.timeAgo = timeAgo;
        this.postContents = postContents;
        this.heart = heart;
        this.comment = comment;
        this.pictureList = pictureList;

    }

    public int getPostNo() {
        return postNo;
    }

    public String  getUserPicture(){
        return userPicture;
    }

    public String getNickName(){
        return nickName;
    }

    public String getPostLocation(){
        return postLocation;
    }

    public String getTimeAgo(){
        return timeAgo;
    }

    public String getPostContents() {
        return postContents;
    }

    public int getHeart(){
        return heart;
    }

    public void setHeart(){
        heart += 1;
    }


    public String getComment(){
        return comment;
    }

    public List<MainPostsResponse.Data.Picture> getPictureList() {
        return pictureList;
    }
}
