package com.example.herethereproject.src.signUp.SignUpRegion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.herethereproject.R;
import com.example.herethereproject.src.BaseActivity;
import com.example.herethereproject.src.signUp.SignUpFinishActivity;
import com.example.herethereproject.src.signUp.SignUpPictureCompleteActivity;
import com.example.herethereproject.src.signUp.SignUpRegion.regionInterfaces.SignUpRegionActivityView;
import com.example.herethereproject.src.signUp.SignUpRegion.regionModels.SignUpRegionResponse;

import java.util.ArrayList;
import java.util.List;


public class SignUpRegionActivity extends BaseActivity implements SignUpRegionActivityView {

    public String[] mSampleList = {"대만", "한국", "미국", "홍콩", "중국", "몽골", "가나", "영국"};

    SignUpRegionAdapter adapter;
    public ArrayList<SignUpRegionItem> itemList;
    public List<Integer> mLocationNo;


    TextView mListTextView;
    ImageView mIvArrow;
    ImageButton mIbtnComplete;
    ListView lvRegion;
    View mBtnList;

    public List<SignUpRegionResponse.data> result = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_region);

        Intent pictureIntent = getIntent();
        //회원가입 api를 위한 intent받기기

        itemList = new ArrayList<SignUpRegionItem>();

        tryPostUser();

        //http주소
        tryGetLocation();

        lvRegion = findViewById(R.id.lv_Sign_up_region);




        mBtnList = findViewById(R.id.btn_sign_up_region_select);
        mIvArrow = findViewById(R.id.iv_sign_up_region_arrow);
        mListTextView = findViewById(R.id.tv_Sign_up_region_list);
        mIbtnComplete = findViewById(R.id.btn_sign_up_region_complete);

        //국가선택이벤트
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
            //선택창 눌러서 리스트 보이기
            case R.id.btn_sign_up_region_select:
                if(lvRegion.getVisibility() == View.VISIBLE){
                    lvRegion.setVisibility(View.GONE);
                    mIvArrow.setImageDrawable(getDrawable(R.drawable.ic_down_arrow));

                    ArrayList<String> name = new ArrayList<String >();
                    mLocationNo = new ArrayList<Integer>();
                    for(int i = 0; i < itemList.size(); i++){
                        if(itemList.get(i).getRegionCheck()){
                            name.add(itemList.get(i).getRegion());
                            mLocationNo.add(itemList.get(i).getRegionNo());
                        }
                    }
                    if(name.size() != 0){
                        String selectRegion = name.get(0);
                        for(int i = 1; i < name.size(); i++){
                            selectRegion = selectRegion.concat(", " + name.get(i));
                        }
                        mListTextView.setText(selectRegion);
                        mIbtnComplete.setImageDrawable(getDrawable(R.drawable.ic_region_complete));
                    } else {
                        mListTextView.setText(R.string.list_select_region);
                        mIbtnComplete.setImageDrawable(getDrawable(R.drawable.ic_select_complete));
                    }

                } else {
                    lvRegion.setVisibility(View.VISIBLE);
                    mIvArrow.setImageDrawable(getDrawable(R.drawable.ic_up_arrow));
                    mListTextView.setText(R.string.list_select_region);
                    mIbtnComplete.setImageDrawable(getDrawable(R.drawable.ic_select_complete));

                }
                break;
            case R.id.btn_sign_up_region_back:
                Intent backIntent = new Intent(getApplicationContext(), SignUpPictureCompleteActivity.class);
                startActivity(backIntent);
                break;
            case R.id.btn_sign_up_region_complete:
                Intent startFinishIntent = new Intent(getApplicationContext(), SignUpFinishActivity.class);
                startActivity(startFinishIntent);
                finish();
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

    private void tryGetLocation() {

        showProgressDialog();
        final SignUpRegionService signUpRegionService = new SignUpRegionService(this);
        signUpRegionService.getLocation();
    }

    private  void tryPostUser() {

        showProgressDialog();
        final SignUpRegionService signUpRegionService = new SignUpRegionService(this);
        signUpRegionService.postUser();
    }

    @Override
    public void validateSuccessGet(List<SignUpRegionResponse.data> result) {

        this.result = result;
        SignUpRegionItem regionItem;

        for(int i = 0; i < result.size(); i++){
            regionItem = new SignUpRegionItem(result.get(i).getLocation(), getDrawable(R.drawable.ic_radio_button_true), getDrawable(R.drawable.ic_radio_button_false), result.get(i).getLocationNo());
            itemList.add(regionItem);
        }
        adapter = new SignUpRegionAdapter(itemList);
        lvRegion.setAdapter(adapter);
        hideProgressDialog();
    }

    @Override
    public void validateSuccessPost(String text){
        System.out.println(text);
        hideProgressDialog();

    }


    @Override
    public void validateFailure(String message) {
        System.out.println(message);
        hideProgressDialog();
    }
}
