package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpRegion.SignUpRegionActivity;

public class SignUpPictureCompleteActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_picture_complete);

    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(getApplicationContext(), SignUpPictureActivity.class);
        startActivity(backIntent);
        finish();
    }

    public void signUpPictureCompleteOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_cPicture_back:
                onBackPressed();
                break;
            case R.id.btn_sign_up_cPicture_complete:
                Intent pictureIntent = getIntent();
                Intent startRegionIntent = new Intent(getApplicationContext(), SignUpRegionActivity.class);
                startRegionIntent.putExtra("email",pictureIntent.getStringExtra("email"));
                startRegionIntent.putExtra("password", pictureIntent.getStringExtra("password"));
                startRegionIntent.putExtra("name", pictureIntent.getStringExtra("name"));
                startRegionIntent.putExtra("birth", pictureIntent.getStringExtra("birth"));
                startRegionIntent.putExtra("nick", pictureIntent.getStringExtra("nick"));
                startRegionIntent.putExtra("school", pictureIntent.getStringExtra("school"));
                startRegionIntent.putExtra("picture", pictureIntent.getStringExtra("picture"));
                startActivity(startRegionIntent);
                finish();
                break;
            case R.id.btn_sign_up_cPicture_pass:
                //일단 하지않음
                //사진인증 넘어가기
                //넘어가면서 앞서 입력한 정보들 인텐트로 넘기기
                break;
            default:
                break;
        }
    }
}
