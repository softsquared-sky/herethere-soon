package com.example.template.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;
import com.example.template.src.login.interfaces.LoginActivityView;

public class LoginActivity extends BaseActivity implements LoginActivityView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }
}
