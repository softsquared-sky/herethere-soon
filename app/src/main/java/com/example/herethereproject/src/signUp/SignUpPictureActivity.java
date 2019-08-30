package com.example.herethereproject.src.signUp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpPictureActivity extends BaseActivity {

    ImageView mPictureImageView;
    ImageButton mCameraImageButton;
    boolean mPictureCheck = false;
    private Uri mCurrentPhotoPath;
    private String mPicture;

    private String imageFilePath;
    private String photoUri;

    private String mFilename;



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
                if (!mPictureCheck) {
                    int permissionCheck = ContextCompat.checkSelfPermission(SignUpPictureActivity.this, Manifest.permission.CAMERA);
                    if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(SignUpPictureActivity.this, new String[]{Manifest.permission.CAMERA}, 0);

                    } else {
                        //takePhoto();

                        dispatchTakePhotoIntent();
                    }
                    break;
                } else if (mPictureCheck) {
                    Intent schoolIntent = getIntent();
                    Intent startPictureCompleteIntent = new Intent(getApplicationContext(), SignUpPictureCompleteActivity.class);
                    startPictureCompleteIntent.putExtra("email", schoolIntent.getStringExtra("email"));
                    startPictureCompleteIntent.putExtra("password", schoolIntent.getStringExtra("password"));
                    startPictureCompleteIntent.putExtra("name", schoolIntent.getStringExtra("name"));
                    startPictureCompleteIntent.putExtra("birth", schoolIntent.getStringExtra("birth"));
                    startPictureCompleteIntent.putExtra("nick", schoolIntent.getStringExtra("nick"));
                    startPictureCompleteIntent.putExtra("school", schoolIntent.getStringExtra("school"));
                    startPictureCompleteIntent.putExtra("picture", mPicture);
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

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            System.out.println(mCurrentPhotoPath);
            uplodeFile(mCurrentPhotoPath);
            mPictureImageView.setImageURI(mCurrentPhotoPath);
            mCameraImageButton.setImageDrawable(getDrawable(R.drawable.ic_school_certification));
            mPictureCheck = true;
        }
    }

    private void downLodeFile(String fileName){
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference downStorageRef = storage.getReferenceFromUrl(fileName);
        showProgressDialog();
        downStorageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                hideProgressDialog();
                mPicture = uri.toString();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("실패");
                hideProgressDialog();
            }
        });
    }

    private void uplodeFile(Uri Uri) {


        if(Uri != null) {

            showProgressDialog();
            FirebaseStorage storage = FirebaseStorage.getInstance();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();

            mFilename = "gs://herethere-soon.appspot.com/picture/" + formatter.format(now) + ".jpg";

            StorageReference upStorageRef = storage.getReferenceFromUrl(mFilename);


            upStorageRef.putFile(mCurrentPhotoPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    hideProgressDialog();
                    downLodeFile(mFilename);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    hideProgressDialog();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        } else {
            showCustomToast("실패");
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TEST_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir );

        imageFilePath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePhotoIntent() {
        Intent takePictureIntent = new Intent();
        takePictureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(photoFile != null){
                mCurrentPhotoPath = FileProvider.getUriForFile(getApplicationContext(), "com.example.herethereproject.fileProvider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,mCurrentPhotoPath);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
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
