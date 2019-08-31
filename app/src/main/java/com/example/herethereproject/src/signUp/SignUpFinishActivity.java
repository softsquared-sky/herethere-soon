package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;

public class SignUpFinishActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
    }

    public void signUpFinishOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_finish_complete:
                finish();
            default:
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
