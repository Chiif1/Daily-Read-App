package com.nugrs.newsapp_dailyread;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.TextView;

public class ExampleItem {
    private String mImageURL;
    private String mText1;
    private String mText2;
    private String mUrlToSite;

    ExampleItem(String image, String text1, String text2, String urlToSite) {
        mImageURL = image;
        mText1 = text1;
        mText2 = text2;
        mUrlToSite = urlToSite;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public String getmUrlToSite() {
        return mUrlToSite;
    }
}
