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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.by_syk.coolapkapi.demo.R;

import java.util.List;

/**
 * Created by By_syk on 2016-11-14.
 */

public class AppsAdapter extends BaseAdapter {
    private List<AppInfo> dataList;

    private LayoutInflater layoutInflater;

    public AppsAdapter(Context context, List<AppInfo> dataList) {
        this.dataList = dataList;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public AppInfo getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_app, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
            viewHolder.tvAppName = (TextView) view.findViewById(R.id.tv_app_name);
            viewHolder.tvScore = (TextView) view.findViewById(R.id.tv_score);
            viewHolder.tvFollow = (TextView) view.findViewById(R.id.tv_follow);
            viewHolder.tvDownload = (TextView) view.findViewById(R.id.tv_download);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        AppInfo appInfo = dataList.get(i);
        viewHolder.ivIcon.setImageDrawable(appInfo.getDrawable());
        viewHolder.tvAppName.setText(appInfo.getAppName());
        viewHolder.tvScore.setText(appInfo.getScore() + "/" + appInfo.getVote());
        viewHolder.tvFollow.setText(String.valueOf(appInfo.getFollow()));
        viewHolder.tvDownload.setText(String.valueOf(appInfo.getDownload()));

        return view;
    }

    public void refreshDataList(List<AppInfo> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvAppName;
        TextView tvScore;
        TextView tvFollow;
        TextView tvDownload;
    }
}
