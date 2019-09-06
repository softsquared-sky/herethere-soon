package com.example.herethereproject.src.signUp.signUpEmain;

        import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpActivityView;
import com.example.herethereproject.src.signUp.signUpInterfaces.SignUpRetrofitInterface;
import com.example.herethereproject.src.signUp.signUpModels.SignUpBody;
import com.example.herethereproject.src.signUp.signUpModels.SignUpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.herethereproject.src.ApplicationClass.getRetrofit;


class SignUpEmailService {
    private final SignUpActivityView mSignUpActivityView;

    SignUpEmailService(final SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
    }


    void postUser(int reqType, String email){
        final SignUpRetrofitInterface signUpRetrofitInterface = getRetrofit().create(SignUpRetrofitInterface.class);

        final SignUpBody signUpBody = new SignUpBody(reqType, email, null,null, null, null, null, null, null, null);


        signUpRetrofitInterface.postUser(signUpBody).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                final SignUpResponse signUpResponse = response.body();
                if (signUpResponse == null) {
                    mSignUpActivityView.validateFailure("null");
                    return;
                }
                mSignUpActivityView.validateSuccessPost(signUpResponse.getIsSuccess(), signUpResponse.getMessage());
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                mSignUpActivityView.validateFailure("fail");
            }
        });
    }
}
