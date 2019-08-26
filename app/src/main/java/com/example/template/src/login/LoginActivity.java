package com.example.template.src.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;
import com.example.template.src.login.interfaces.LoginActivityView;
import com.example.template.src.signUp.SignUpEmailActivity;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText et_login_email;
    EditText et_login_password;
    ImageButton btn_login;
    boolean login_check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_login_email = findViewById(R.id.login_email_input);
        et_login_password = findViewById(R.id.login_password_input);
        btn_login = findViewById(R.id.login_button);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkPassword(et_login_password.getText().toString()) && checkEmail(et_login_email.getText().toString())) {
                    //올바른 입력
                    System.out.println("success");
                    btn_login.setImageDrawable(getDrawable(R.drawable.ic_login_yes));
                    login_check = true;
                } else {
                    //잘못된 입력
                    System.out.println("fail");
                    btn_login.setImageDrawable(getDrawable(R.drawable.ic_login_none));
                    login_check = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_login_email.addTextChangedListener(textWatcher);
        et_login_password.addTextChangedListener(textWatcher);


    }

    public void loginOnClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                if(login_check){
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
}
