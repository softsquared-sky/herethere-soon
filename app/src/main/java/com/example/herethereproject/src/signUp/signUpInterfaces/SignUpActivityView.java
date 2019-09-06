package com.example.herethereproject.src.signUp.signUpInterfaces;

import com.example.herethereproject.src.signUp.signUpModels.SignUpRegionResponse;

import java.util.List;

public interface SignUpActivityView {

    void validateSuccessGet(List<SignUpRegionResponse.data> result);

    void validateSuccessPost(boolean success, String message);

    void validateFailure(String message);
}
