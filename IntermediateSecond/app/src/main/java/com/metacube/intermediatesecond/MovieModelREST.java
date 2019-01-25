package com.metacube.intermediatesecond;

import android.graphics.Bitmap;

public class MovieModelREST {
    private String title;
    private Bitmap thumb;
    private String url;

    public MovieModelREST(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public MovieModelREST(String title, Bitmap thumb) {
        this.title = title;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public String getUrl() {
        return url;
    }
}
