package com.draw.tales.classes;

/**
 * Created by KorbBookProReturns on 7/11/17.
 */

public class CoverInfo {
    private String mUserName, mUserId;
    private int mCover;

    public CoverInfo() {
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public int getCover() {
        return mCover;
    }

    public void setCover(int cover) {
        mCover = cover;
    }
}
