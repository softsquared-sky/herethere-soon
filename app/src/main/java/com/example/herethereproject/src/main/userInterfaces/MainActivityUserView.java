package com.example.herethereproject.src.main.userInterfaces;

import com.example.herethereproject.src.main.mainHome.postsModels.MainPostsResponse;
import com.example.herethereproject.src.main.userModels.MainUserPictureResponse;
import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import java.util.List;

public interface MainActivityUserView {

    void validateUserProfileSuccess(MainUserProfileResponse.Result result);

    void validateUserpostsSuccess(String message, List<MainPostsResponse.Data> result, int code);

    void validateUserPictureSuccess(String message, List<MainUserPictureResponse.Result> result, int code);

    void validateUserProfileFailure(String message);
}
