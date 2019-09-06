package com.example.herethereproject.src.main.userInterfaces;

import com.example.herethereproject.src.main.userModels.MainUserProfileResponse;

import java.util.List;

public interface MainActivityUserView {

    void validateUserProfileSuccess(List<MainUserProfileResponse.Result> result);

    void validateUserProfileFailure(String message);
}
