package com.example.herethereproject.src.signUp.SignUpRegion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.herethereproject.R;

import java.util.ArrayList;

public class SignUpRegionAdapter extends BaseAdapter {
    public ArrayList<SignUpRegionItem> data = new ArrayList<SignUpRegionItem>();


    public SignUpRegionAdapter(ArrayList<SignUpRegionItem> data){
        this.data = data;
//        System.out.println(data.size());
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        //System.out.println("ASDFA");
        final  Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_region, viewGroup, false);
        }



        SignUpRegionItem signUpRegionItem = data.get(position);
        TextView tvitem = view.findViewById(R.id.tv_sign_up_item);
        tvitem.setText(signUpRegionItem.getRegion());

        ImageView ivItem = view.findViewById(R.id.iv_sign_up_item);
        if(signUpRegionItem.getRegionCheck()){
            ivItem.setImageDrawable(signUpRegionItem.trueDrawable);
        } else {
            ivItem.setImageDrawable(signUpRegionItem.falseDrawable);
        }


        return view;
    }
}