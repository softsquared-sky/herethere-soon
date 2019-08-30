package com.example.herethereproject.src.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;

import java.util.ArrayList;


public class SignUpRegionActivity extends BaseActivity {

    public String[] mSampleList = {"대만", "한국", "미국", "홍콩", "중국", "몽골", "가나", "영국"};
    public ListView lvRegion;
    public View mBtnList;
    SignUpRegionAdapter adapter;
    private boolean clickCheck = false;
    ImageView mIvArrow;
    public ArrayList<SignUpRegionItem> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_region);

        lvRegion = findViewById(R.id.lv_Sign_up_region);

        SignUpRegionItem regionItem;
        itemList = new ArrayList<SignUpRegionItem>();

        for(int i = 0; i < 8; i++){
            regionItem = new SignUpRegionItem(mSampleList[i], getDrawable(R.drawable.ic_radio_button_true), getDrawable(R.drawable.ic_radio_button_false));
            itemList.add(regionItem);
        }

        adapter = new SignUpRegionAdapter(itemList);

        lvRegion.setAdapter(adapter);

        mBtnList = findViewById(R.id.btn_sign_up_region_select);
        mIvArrow = findViewById(R.id.iv_sign_up_region_arrow);

        lvRegion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(itemList.get(i).getRegionCheck()){
                    itemList.get(i).setRegionCheck(false);
                } else {
                    itemList.get(i).setRegionCheck(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void signUpRegionOnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_up_region_select:
                if(lvRegion.getVisibility() == View.VISIBLE){
                    lvRegion.setVisibility(View.GONE);
                    mIvArrow.setImageDrawable(getDrawable(R.drawable.ic_down_arrow));
                } else {
                    lvRegion.setVisibility(View.VISIBLE);
                    mIvArrow.setImageDrawable(getDrawable(R.drawable.ic_up_arrow));
                    int count = 0;
                    ArrayList<String> name = new ArrayList<String >();
                    for(int i = 0; i < itemList.size(); i++){
                        if(itemList.get(i).getRegionCheck()){
                            count++;
                            name.add(itemList.get(i).getRegion());
                        }
                    }
                    if(count != 0){
                        String a;

                    }
                }
                break;
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
