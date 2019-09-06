package com.example.herethereproject.src.main.userInterfaces;

import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

public interface MainActivityUserView {

    void validateUserProfileSuccess(MainUserProfileResponse.Result result);

    void validateUserProfileFailure(String message);
}
