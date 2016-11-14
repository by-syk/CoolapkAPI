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

package com.by_syk.coolapkapi;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by By_syk on 2016-11-14.
 */

public class CoolapkApiV2 {
    public static JSONObject getApkField(int appId) {
        //String appId = "80709";
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("http://api.coolapk.com/market/v2/api.php" +
                    "?apikey=5b90704e1db879af6f5ee08ec1e8f2a5" +
                    "&method=getApkField" +
                    "&includeMeta=0" +
                    "&slm=1" +
                    "&id=" + appId);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(4000);
            huc.setReadTimeout(4000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.1 (Linux; Android 5.1; Nexus 5 Build/LRX22C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.124 Mobile Safari/537.36 +CoolMarket/2.5.4");
            huc.setRequestProperty("Cookie", "coolapk_did=d41d8cd98f00b204e9800998ecf8427e");

            bufferedReader = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer).append("\n");
            }

            return new JSONObject(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static JSONObject getApkMeta(String packageName) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL("http://api.coolapk.com/market/v2/api.php" +
                    "?apikey=5b90704e1db879af6f5ee08ec1e8f2a5" +
                    "&method=getApkMeta" +
                    "&qt=apkname" +
                    "&slm=1" +
                    "&v=" + packageName);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setConnectTimeout(4000);
            huc.setReadTimeout(4000);
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.1 (Linux; Android 5.1; Nexus 5 Build/LRX22C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.124 Mobile Safari/537.36 +CoolMarket/2.5.4");
            huc.setRequestProperty("Cookie", "coolapk_did=d41d8cd98f00b204e9800998ecf8427e");

            bufferedReader = new BufferedReader(new InputStreamReader(huc.getInputStream(), "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer).append("\n");
            }

            if (stringBuilder.length() == 0) {
                return null;
            }
            return new JSONObject(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
