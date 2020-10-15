package com.example.done;

public class ItemChat {
    private  int mImageResource ;
    private String mUser;
    private String mHach;

    public ItemChat(int mImageResource, String mUser, String mHach) {
        this.mImageResource = mImageResource;
        this.mUser = mUser;
        this.mHach = mHach;
    }
    public String getmUser() {
        return mUser;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmHach() {
        return mHach;
    }
}
