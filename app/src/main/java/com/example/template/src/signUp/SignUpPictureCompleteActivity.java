package com.example.template.src.signUp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

import java.io.File;

public class SignUpPictureCompleteActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_picture_complete);

    }

    public void signUpPictureCompleteOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_cPicture_back:
                finish();
                break;
            case R.id.btn_sign_up_cPicture_complete:
                Intent pictureIntent = getIntent();
                Intent startRegionIntent = new Intent(getApplicationContext(), SignUpRegionActivity.class);
                startRegionIntent.putExtra("email",pictureIntent.getStringExtra("email"));
                startRegionIntent.putExtra("password", pictureIntent.getStringExtra("password"));
                startRegionIntent.putExtra("name", pictureIntent.getStringExtra("name"));
                startRegionIntent.putExtra("birth", pictureIntent.getStringExtra("birth"));
                startRegionIntent.putExtra("nick", pictureIntent.getStringExtra("nick"));
                startRegionIntent.putExtra("picture", pictureIntent.getStringExtra("picture"));
                startActivity(startRegionIntent);
                //.putExtra("picture", mPictureImageView.getImageAlpha());
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
