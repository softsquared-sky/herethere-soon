package com.example.herethereproject.src.main.mainHome.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.herethereproject.R;

import java.util.ArrayList;

public class PostsPictureAdapter  extends RecyclerView.Adapter<PostsPictureAdapter.HomeViewHolder> {

    private ArrayList<PostsPictureItem> mPictureList;

    public PostsPictureAdapter(ArrayList<PostsPictureItem> pictureList){
        this.mPictureList = pictureList;
    }


    @NonNull
    @Override
    public PostsPictureAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home_picture, parent, false);




        return new PostsPictureAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsPictureAdapter.HomeViewHolder holder, int position) {
        holder.onBind(mPictureList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPictureList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{
        ImageButton pictureImageButton;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            pictureImageButton = itemView.findViewById(R.id.btn_main_home_picture);
        }


        void onBind(PostsPictureItem postsPictureItem){
            Glide.with(itemView)
                    .load(postsPictureItem.getPostPicture())
                    .into(pictureImageButton);
        }

    }
}
