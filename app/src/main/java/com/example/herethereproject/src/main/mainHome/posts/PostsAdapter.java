package com.example.herethereproject.src.main.mainHome.posts;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.herethereproject.R;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.HomeViewHolder> {

    private ArrayList<PostsItem> mCommentList;

    public PostsAdapter(ArrayList<PostsItem> list){
        this.mCommentList = list;

    }

    @NonNull
    @Override
    public PostsAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts_comment, parent, false);

        return new PostsAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.HomeViewHolder holder, int position) {

        holder.onBind(mCommentList.get(position));
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView timeTextView;
        TextView userCommentTextView;



        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            timeTextView = itemView.findViewById(R.id.tv_comment_timeAgo);
            userCommentTextView = itemView.findViewById(R.id.tv_comment_comment);
        }


        void onBind(PostsItem postsItem){
            String userTime = "<b>" + postsItem.getNickName() + "</b>" + " " + postsItem.getComment();




            userCommentTextView.setText(Html.fromHtml(userTime));
            timeTextView.setText(postsItem.getTimeAgo());


        }

    }
}
