package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;

public class SignUpRegionActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_region);

    }

    public void signUpRegionOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_region_back:
                onBackPressed();
                break;
            case R.id.btn_sign_up_region_complete:
                Intent pictureIntent = getIntent();
                /*
                Intent startRegionIntent = new Intent(getApplicationContext(), SignUpRegionActivity.class);
                startRegionIntent.putExtra("email",pictureIntent.getStringExtra("email"));
                startRegionIntent.putExtra("password", pictureIntent.getStringExtra("password"));
                startRegionIntent.putExtra("name", pictureIntent.getStringExtra("name"));
                startRegionIntent.putExtra("birth", pictureIntent.getStringExtra("birth"));
                startRegionIntent.putExtra("nick", pictureIntent.getStringExtra("nick"));
                startRegionIntent.putExtra("picture", pictureIntent.getStringExtra("picture"));
                startActivity(startRegionIntent);
                */
                //.putExtra("picture", mPictureImageView.getImageAlpha());
                break;
            case R.id.btn_sign_up_region_pass:
                //일단 하지않음
                //사진인증 넘어가기
                //넘어가면서 앞서 입력한 정보들 인텐트로 넘기기
                break;
            default:
                break;
        }
    }
}
