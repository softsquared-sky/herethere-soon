package com.example.template.src.signUp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.herethereproject.R;
import com.example.template.src.BaseActivity;

import java.io.File;
import java.io.OutputStream;

public class SignUpPictureActivity extends BaseActivity {

    private Camera mCamera;
    ImageView mPictureImageView;
    ImageButton mCameraImageButton;
    boolean mPictureCheck = false;
    private File file;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_picture);

        mCameraImageButton = findViewById(R.id.btn_sign_up_picture_complete);
        mPictureImageView = findViewById(R.id.iv_sign_up_picture_image);







    }

    public void signUpPictureOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_picture_back:
                finish();
                break;
            case R.id.btn_sign_up_picture_complete:
                if(!mPictureCheck){
                    int permissionCheck = ContextCompat.checkSelfPermission(SignUpPictureActivity.this, Manifest.permission.CAMERA);
                    if(permissionCheck == PackageManager.PERMISSION_DENIED){
                        ActivityCompat.requestPermissions(SignUpPictureActivity.this, new String[]{Manifest.permission.CAMERA},0);

                    } else {
                        dispatchTakePhotoIntent();
                    }
                    break;
                } else if(mPictureCheck){
                    Intent privacyIntent = getIntent();
                    Intent startPictureCompleteIntent = new Intent(getApplicationContext(), SignUpPictureCompleteActivity.class);
                    startPictureCompleteIntent.putExtra("email",privacyIntent.getStringExtra("email"));
                    startPictureCompleteIntent.putExtra("password", privacyIntent.getStringExtra("password"));
                    startPictureCompleteIntent.putExtra("name", privacyIntent.getStringExtra("name"));
                    startPictureCompleteIntent.putExtra("birth", privacyIntent.getStringExtra("birth"));
                    startPictureCompleteIntent.putExtra("nick", privacyIntent.getStringExtra("nick"));
                    startPictureCompleteIntent.putExtra("picture", mPictureImageView.getDrawable().toString());
                    //startPictureCompleteIntent.putExtra("picture", mPictureImageView.getImageAlpha());
                    startActivity(startPictureCompleteIntent);
                }


                break;
            case R.id.btn_sign_up_picture_pass:
                //일단 하지않음
                //사진인증 넘어가기
                //넘어가면서 앞서 입력한 정보들 인텐트로 넘기기
                break;
            default:
                break;
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mPictureImageView.setImageBitmap(imageBitmap);
            mCameraImageButton.setImageDrawable(getDrawable(R.drawable.ic_school_certification));
            mPictureCheck = true;
        }
    }

    private void dispatchTakePhotoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Uri uri = FileProvider.getUriForFile(getBaseContext(), "com.example.herethereproject.fileprovider",file);
        //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {

            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 0){
            if(grantResults[0] == 0){
                dispatchTakePhotoIntent();
            } else {
                showCustomToast("실패");
            }
        }
    }

}
