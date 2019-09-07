package com.example.herethereproject.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.login.loginInterfaces.LoginActivityView;
import com.example.herethereproject.src.login.loginModels.LoginBody;
import com.example.herethereproject.src.main.MainActivity;
import com.example.herethereproject.src.signUp.signUpEmain.SignUpEmailActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText mLoginEmailEditText;
    EditText mLoginPasswordEditText;
    ImageButton mImageButton;
    boolean mLoginCheck = false;

    public LoginBody mLoginBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent logoutIntent = getIntent();

        if(logoutIntent.getBooleanExtra("logout", false)){
            TextView loginTextView = findViewById(R.id.login_text);
            loginTextView.setText(getString(R.string.logout));
        }

        mLoginEmailEditText = findViewById(R.id.login_email_input);
        mLoginPasswordEditText = findViewById(R.id.login_password_input);
        mImageButton = findViewById(R.id.login_button);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkPassword(mLoginPasswordEditText.getText().toString()) && checkEmail(mLoginEmailEditText.getText().toString())) {
                    //올바른 입력
                    //System.out.println("success");
                    mImageButton.setImageDrawable(getDrawable(R.drawable.ic_login_yes));
                    mLoginCheck = true;
                } else {
                    //잘못된 입력
                    //System.out.println("fail");
                    mImageButton.setImageDrawable(getDrawable(R.drawable.ic_login_none));
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
                    tryPostJwt();
                    break;
                } else {
                    break;
                }

            case R.id.login_sign_up:
                Intent start_sign_up_intent = new Intent(getApplicationContext(), SignUpEmailActivity.class);
                startActivity(start_sign_up_intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void tryPostJwt() {
        //mLoginBody = new LoginBody("test@naver.com","q1w2e3");

        final LoginService loginService = new LoginService(this);
        loginService.postJwt(mLoginEmailEditText.getText().toString(), mLoginPasswordEditText.getText().toString());
        showProgressDialog();
    }

    @Override
    public void validateSuccess(String message, boolean success) {
        if(success){
            showCustomToast(message);
            Intent startMainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startMainIntent.putExtra("email", mLoginEmailEditText.getText().toString());
            startActivity(startMainIntent);
            finish();

        } else{
            showCustomToast(message);
        }

        hideProgressDialog();
    }

    @Override
    public void validateFailure(String message) {
        System.out.println(message);
        hideProgressDialog();
    }
}
