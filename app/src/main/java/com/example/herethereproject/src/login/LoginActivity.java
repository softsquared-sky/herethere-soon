package com.example.herethereproject.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.herethereproject.R;
import com.example.herethereproject.src.login.interfaces.LoginActivityView;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpEmailActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText mLoginEmailEditText;
    EditText mLoginPasswordEditText;
    ImageButton mImgaeButton;
    boolean mLoginCheck = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mLoginEmailEditText = findViewById(R.id.login_email_input);
        mLoginPasswordEditText = findViewById(R.id.login_password_input);
        mImgaeButton = findViewById(R.id.login_button);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkPassword(mLoginPasswordEditText.getText().toString()) && checkEmail(mLoginEmailEditText.getText().toString())) {
                    //올바른 입력
                    System.out.println("success");
                    mImgaeButton.setImageDrawable(getDrawable(R.drawable.ic_login_yes));
                    mLoginCheck = true;
                } else {
                    //잘못된 입력
                    System.out.println("fail");
                    mImgaeButton.setImageDrawable(getDrawable(R.drawable.ic_login_none));
                    mLoginCheck = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        mLoginEmailEditText.addTextChangedListener(textWatcher);
        mLoginPasswordEditText.addTextChangedListener(textWatcher);


    }

    public void loginOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if(mLoginCheck){
                    //로그인 성공, 메인화면으로 이동
                    break;
                } else {
                    break;
                }

            case R.id.login_sign_up:
                Intent start_sign_up_intent = new Intent(getApplicationContext(), SignUpEmailActivity.class);
                startActivity(start_sign_up_intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }
}
