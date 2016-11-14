package com.by_syk.coolapkapi.demo.util;

import android.graphics.drawable.Drawable;

/**
 * Created by By_syk on 2016-11-14.
 */

public class AppInfo {
    private String packageName;
    private String appName;
    private Drawable drawable;
    private float score = -1f;
    private int vote = -1;
    private int follow = -1;
    private int download = -1;

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public void setDownload(int download) {
        this.download = download;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getAppName() {
        return appName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public float getScore() {
        return score;
    }

    public int getVote() {
        return vote;
    }

    public int getFollow() {
        return follow;
    }

    public int getDownload() {
        return download;
    }
}
