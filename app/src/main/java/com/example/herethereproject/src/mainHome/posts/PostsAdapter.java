package com.example.herethereproject.src.mainHome.posts;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.HomeViewHolder> {
    @NonNull
    @Override
    public PostsAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.HomeViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{



        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);





        }


        void onBind(PostsItem postsItem){


        }

    }
}
