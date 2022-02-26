package com.android.farmerscommunity;

import android.net.Uri;

public class Post {
    private int mImageResource;
    private Uri mNewImage;
    private String mCategory;
    private String mQuestion;
    private String mUserName;
    private int noAnswers;

    public Post(int imageRes, String category, String question, String user, int answers){
        mImageResource = imageRes;
        mCategory = category;
        mQuestion = question;
        mUserName = user;
        noAnswers = answers;
        mNewImage = null;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getUserName() {
        return mUserName;
    }

    public int getNoAnswers() {
        return noAnswers;
    }

    public Uri getNewImage() {
        return mNewImage;
    }
}
