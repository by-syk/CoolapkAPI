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

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.by_syk.coolapkapi.CoolapkApiV2;
import com.by_syk.coolapkapi.demo.util.DateUtil;

import org.json.JSONObject;

/**
 * Created by By_syk on 2016-11-14.
 */

public class Sample1Activity extends Activity {
    private Button btQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample1);

        init();
    }

    private void init() {
        btQuery = (Button) findViewById(R.id.bt_query);
    }

    public void onMyClick(View view) {
        switch (view.getId()) {
            case R.id.bt_query: {
                String text = ((EditText) findViewById(R.id.et_query)).getText().toString();
                (new LoadDataTask()).execute(text);
                break;
            }
        }
    }

    class LoadDataTask extends AsyncTask<String, Integer, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            btQuery.setEnabled(false);
            btQuery.setText(R.string.querying);
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            return CoolapkApiV2.getApkMeta(strings[0]);
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            btQuery.setEnabled(true);
            btQuery.setText(R.string.query);

            fillData(jsonObject);
        }
    }

    private void fillData(JSONObject jsonObject) {
        if (jsonObject == null) {
            return;
        }

        ((TextView) findViewById(R.id.tv_id)).setText(jsonObject.optString("id"));
        ((TextView) findViewById(R.id.tv_pkg_name)).setText(jsonObject.optString("apkname"));
//        ((TextView) findViewById(R.id.tv_app_name)).setText(jsonObject.optString(""));
        ((TextView) findViewById(R.id.tv_ver_name)).setText(jsonObject.optString("apkversionname"));
        ((TextView) findViewById(R.id.tv_ver_code)).setText(jsonObject.optString("apkversioncode"));
        ((TextView) findViewById(R.id.tv_apk_size)).setText(jsonObject.optString("apksize"));
        ((TextView) findViewById(R.id.tv_title)).setText(jsonObject.optString("title"));
        ((TextView) findViewById(R.id.tv_desc)).setText(jsonObject.optString("description"));
        ((TextView) findViewById(R.id.tv_developer)).setText(jsonObject.optString("developername"));
        ((TextView) findViewById(R.id.tv_score)).setText(jsonObject.optString("score"));
        ((TextView) findViewById(R.id.tv_vote)).setText(jsonObject.optString("votenum"));
        ((TextView) findViewById(R.id.tv_follow)).setText(jsonObject.optString("favnum"));
        ((TextView) findViewById(R.id.tv_comment)).setText(jsonObject.optString("commentnum"));
        ((TextView) findViewById(R.id.tv_download)).setText(jsonObject.optString("downnum"));
        ((TextView) findViewById(R.id.tv_release))
                .setText(DateUtil.getDateStr(jsonObject.optLong("pubdate") * 1000, "yyyy-MM-dd"));
        ((TextView) findViewById(R.id.tv_update))
                .setText(DateUtil.getDateStr(jsonObject.optLong("lastupdate") * 1000, "yyyy-MM-dd"));
    }
}
