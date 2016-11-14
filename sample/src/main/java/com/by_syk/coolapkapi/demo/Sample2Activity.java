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

package com.by_syk.coolapkapi.demo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.by_syk.coolapkapi.CoolapkApiV2;
import com.by_syk.coolapkapi.demo.util.AppInfo;
import com.by_syk.coolapkapi.demo.util.AppsAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by By_syk on 2016-11-14.
 */

public class Sample2Activity extends Activity {
    private ActionBar actionBar;

    private ListView listView;

    private List<AppInfo> dataList;
    private AppsAdapter appsAdapter;

    private boolean reqCancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);

        init();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        (new LoadDataTask()).execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        reqCancel = true;
    }

    private void init() {
        actionBar = getActionBar();

        listView = (ListView) findViewById(R.id.lv_apps);
        listView.setEmptyView(findViewById(R.id.ll_loading));

        dataList = new ArrayList<>();
        appsAdapter = new AppsAdapter(this, dataList);
        listView.setAdapter(appsAdapter);
    }

    class LoadDataTask extends AsyncTask<String, AppInfo, Boolean> {
        private int progress;
        private int total;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dataList.clear();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            PackageManager packageManager = getPackageManager();
            synchronized(Sample2Activity.class) {
                List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
                total = packageInfoList.size();
                for (progress = 0; progress < total; ++progress) {
                    if (reqCancel) {
                        return false;
                    }

                    PackageInfo packageInfo = packageInfoList.get(progress);
                    if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)
                            == ApplicationInfo.FLAG_SYSTEM) {
                        continue;
                    }

                    JSONObject jsonObject = CoolapkApiV2.getApkMeta(packageInfo.packageName);
                    if (jsonObject == null) {
                        publishProgress();
                        continue;
                    }

                    AppInfo appInfo = new AppInfo();

                    appInfo.setScore(Float.parseFloat(jsonObject.optString("score")));
                    appInfo.setFollow(jsonObject.optInt("favnum"));
                    appInfo.setVote(jsonObject.optInt("votenum"));
                    appInfo.setDownload(jsonObject.optInt("downnum"));

                    appInfo.setPackageName(packageInfo.packageName);
                    appInfo.setAppName(packageManager.getApplicationLabel(packageInfo.applicationInfo).toString());
                    try {
                        appInfo.setDrawable(packageManager.getApplicationIcon(packageInfo.packageName));
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }

                    dataList.add(0, appInfo);
                    publishProgress();
                }

                Collections.sort(dataList, new Comparator<AppInfo>() {
                    @Override
                    public int compare(AppInfo appInfo1, AppInfo appInfo2) {
                        if (appInfo2.getScore() > appInfo1.getScore()) {
                            return 1;
                        } else if (appInfo2.getScore() == appInfo1.getScore()) {
                            if (appInfo2.getFollow() > appInfo1.getFollow()) {
                                return 1;
                            } else if (appInfo2.getFollow() == appInfo1.getFollow()) {
                                if (appInfo2.getDownload() > appInfo1.getDownload()) {
                                    return 1;
                                } else if (appInfo2.getDownload() == appInfo1.getDownload()) {
                                    return 0;
                                }
                            }
                        }
                        return -1;
                    }
                });
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(AppInfo... values) {
            super.onProgressUpdate(values);

            actionBar.setSubtitle(String.format(Locale.US, "%1$d/%2$d/%3$d...",
                    dataList.size(), progress + 1, total));

            appsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            actionBar.setSubtitle(null);

            appsAdapter.notifyDataSetChanged();
        }
    }
}
