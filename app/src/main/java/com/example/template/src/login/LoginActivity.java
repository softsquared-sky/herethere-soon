package com.example.template.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;
import com.example.template.src.login.interfaces.LoginActivityView;

import org.w3c.dom.Text;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    EditText et_email;
    EditText et_password;
    ImageButton btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_email = findViewById(R.id.login_email_input);
        et_password = findViewById(R.id.login_password_input);
        btn_login = findViewById(R.id.login_button);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (checkPassword(et_password.getText().toString()) && checkEmail(et_email.getText().toString())) {
                    //올바른 입력
                    System.out.println("success");
                    btn_login.setImageDrawable(getDrawable(R.drawable.ic_login_yes));
                } else {
                    //잘못된 입력
                    System.out.println("fail");
                    btn_login.setImageDrawable(getDrawable(R.drawable.ic_login_none));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        et_email.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);


    }
}
