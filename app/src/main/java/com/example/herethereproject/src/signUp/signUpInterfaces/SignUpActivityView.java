package com.example.herethereproject.src.signUp.SignUpInterfaces;

import com.example.herethereproject.src.signUp.SignUpModels.SignUpRegionResponse;

import java.util.List;

public interface SignUpActivityView {

    void validateSuccessGet(List<SignUpRegionResponse.data> result);

    void validateSuccessPost(boolean success, String message);

    void validateFailure(String message);
}
