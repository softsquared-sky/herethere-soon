package com.example.template.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

public class SignUpPictureActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_picture);

    }

    public void signUpPictureOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_picture_back:
                finish();
                break;
            case R.id.btn_sign_up_picture_complete:
                //사진어플 접근
                break;
            case R.id.btn_sign_up_picture_pass:
                Intent privacy_intent = getIntent();
                Intent start_region_intent;
                //사진인증 넘어가기
                //넘어가면서 앞서 입력한 정보들 인텐트로 넘기기
                break;
            default:
                break;
        }
    }
}
