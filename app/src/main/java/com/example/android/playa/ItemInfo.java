package com.example.android.playa;

import android.content.Context;

public class ItemInfo {
    private String mTitle;
    private String mByLine;
    private String mLength;
    private Context mContext;

    public void TextView(Context context) {
        mTitle = "";
        mByLine = "";
        mLength = "";
        mContext = context;
    }

    public ItemInfo(String mediaTitle, String mediaByLine, String mediaLength) {
        mTitle = mediaTitle;
        mByLine = mediaByLine;
        mLength = mediaLength;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getByLine() {
        return mByLine;
    }

    public String getLength() {
        return mLength;
    }

    public String getTitleAndArtist() {
        return mTitle + " ... " + mByLine;
    }
}
