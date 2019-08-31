package com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces;

import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResultResponse;

import java.util.ArrayList;

public interface SignUpRegionActivityView {

    void validateSuccess(ArrayList<SignUpRegionResultResponse> result);

    void validateFailure(String message);
}
