/**
 * Copyright 2016 By_syk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
