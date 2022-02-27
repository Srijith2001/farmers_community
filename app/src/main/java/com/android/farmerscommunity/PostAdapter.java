package com.android.farmerscommunity;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.PathUtils;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private ArrayList<Post> mPosts;

    public static class PostViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mCategoryView;
        public TextView mQuestionView;
        public TextView mUserNameView;
        public TextView noAnswersView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.photo);
            mCategoryView = itemView.findViewById(R.id.category);
            mQuestionView = itemView.findViewById(R.id.question);
            mUserNameView = itemView.findViewById(R.id.username);
            noAnswersView = itemView.findViewById(R.id.answer_count);
        }
    }

    public PostAdapter(ArrayList<Post> posts){
        mPosts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false);
        PostViewHolder pvh = new PostViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentPost = mPosts.get(position);

        if(currentPost.getNewImage()==null)
            holder.mImageView.setImageResource(currentPost.getImageResource());
        else {
            holder.mImageView.setImageURI(Uri.parse(currentPost.getNewImage()));
        }
        holder.mCategoryView.setText(currentPost.getCategory());
        holder.mQuestionView.setText(currentPost.getQuestion());
        holder.mUserNameView.setText("Posted by " + currentPost.getUserName()+" on 24-Oct-2021");
        holder.noAnswersView.setText(currentPost.getNoAnswers()+" Answers");
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
