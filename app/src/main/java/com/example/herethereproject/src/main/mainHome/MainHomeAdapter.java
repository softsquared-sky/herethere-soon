package com.example.herethereproject.src.main.mainHome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;

import java.util.ArrayList;

public class MainHomeAdapter extends RecyclerView.Adapter<MainHomeAdapter.HomeViewHolder>{

    public ArrayList<MainHomeItem> homeList;

    public MainHomeAdapter(ArrayList<MainHomeItem> homeList){
        this.homeList = homeList;

    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("in");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_home, parent, false);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        System.out.println("holder");
        holder.onBind(homeList.get(position));
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    void addItem(MainHomeItem mainHomeItem){
        System.out.println("add");
        homeList.add(mainHomeItem);
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        private ImageView profile;
        private TextView name;
        private TextView regionTime;
        private TextView line;
        private TextView heart;
        private TextView bookMark;
        private TextView comment;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.iv_main_home_profile);
            name = itemView.findViewById(R.id.tv_main_home_profile_name);
            regionTime = itemView.findViewById(R.id.tv_main_home_region);
            line = itemView.findViewById(R.id.tv_main_home_line);
            heart = itemView.findViewById(R.id.tv_main_home_heart);
            bookMark = itemView.findViewById(R.id.tv_main_home_bookmark);
            comment = itemView.findViewById(R.id.tv_main_home_comment);



        }

        void onBind(MainHomeItem mainHomeItem){
            System.out.println("bind");
            profile.setImageResource(mainHomeItem.getProfilePicture());
            name.setText(mainHomeItem.getNickName());
            regionTime.setText("asdf");
            line.setText("asdf");
            heart.setText("asdf");
            bookMark.setText("asdf");
            comment.setText("asdf");
        }

    }
}