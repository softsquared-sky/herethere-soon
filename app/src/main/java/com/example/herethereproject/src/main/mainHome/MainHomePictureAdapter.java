package com.example.herethereproject.src.main.mainHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.herethereproject.R;

import java.util.ArrayList;

public class MainHomePictureAdapter extends RecyclerView.Adapter<MainHomePictureAdapter.HomeViewHolder>{

    private MainHomeItem mMainHomeItem;
    private ArrayList<MainHomePictureItem> mPictureList;

    public MainHomePictureAdapter(ArrayList<MainHomePictureItem> pictureList){
        this.mPictureList = pictureList;
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home_picture, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(mPictureList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPictureList.size();
    }


    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageButton postPictureImageButton;


        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);


            postPictureImageButton =itemView.findViewById(R.id.btn_main_home_picture);


        }

        void onBind(MainHomePictureItem mainHomePictureItem) {
            Glide.with(itemView)
                    .load(mainHomePictureItem.getProfilePicture())
                    .into(postPictureImageButton);
        }
    }
}