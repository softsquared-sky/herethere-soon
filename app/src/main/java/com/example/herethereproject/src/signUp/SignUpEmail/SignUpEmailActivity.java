package com.example.herethereproject.src.signUp.SignUpEmail;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.login.LoginActivity;
import com.example.herethereproject.src.signUp.SignUpInterfaces.SignUpActivityView;
import com.example.herethereproject.src.signUp.SignUpModels.SignUpRegionResponse;
import com.example.herethereproject.src.signUp.SignUpPasswordActivity;

import java.util.List;

public class SignUpEmailActivity extends BaseActivity implements SignUpActivityView {
    EditText mSignUpEmailEditText;
    boolean mEmailCheck = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);

        mSignUpEmailEditText = findViewById(R.id.et_sign_up_email_input);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(checkEmail(mSignUpEmailEditText.getText().toString())){
                    System.out.println("sign success");
                    mEmailCheck = true;
                } else {
                    mEmailCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        mSignUpEmailEditText.addTextChangedListener(textWatcher);


    }

    public void signUpEmailOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_email_back:
                Intent startLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(startLoginIntent);
                finish();
                break;
            case R.id.btn_sign_up_email_complete:
                if(mEmailCheck){
                    tryPostUser();
                    break;
                    //비밀번호 입력으로 전환
                } else{
                    showCustomToast(getString(R.string.toast_wrong_message));
                    break;
                }
            default:
                break;
        }
    }

    private  void tryPostUser() {

        showProgressDialog();
        final SignUpEmailService signUpEmailService = new SignUpEmailService(this);

        int reqType = 0;
        String email = mSignUpEmailEditText.getText().toString();
        signUpEmailService.postUser(reqType, email);
        //signUpRegionService.postUser("randy3456@naver.com", "q1w2e3", "홍순재", 960603, "asdf321", "asdf.jpg", "한국항", locationNo);
    }


    @Override
    public void validateSuccessPost(boolean success, String message) {
        hideProgressDialog();
        if(message != null) {

            showCustomToast(message);
        } else {
            showCustomToast(message);
        }

    }

    @Override
    public void validateFailure(String message) {
        hideProgressDialog();
        Intent start_sign_up_password_intent = new Intent(getApplicationContext(), SignUpPasswordActivity.class);
        start_sign_up_password_intent.putExtra("email", mSignUpEmailEditText.getText().toString());
        startActivity(start_sign_up_password_intent);
        finish();
    }

    @Override
    public void validateSuccessGet(List<SignUpRegionResponse.data> result) {

    }
}
