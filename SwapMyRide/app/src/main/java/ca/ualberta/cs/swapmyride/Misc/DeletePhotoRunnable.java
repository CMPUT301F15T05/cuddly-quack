/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Given a photoId and a url the photo is attempted to be deleted from the server
 *
 * Created by Garry on 2015-11-29.
 */
public class DeletePhotoRunnable implements Runnable {
    private String url;

    public DeletePhotoRunnable(String photoId, String url) {
        this.url = url + "photos/" + photoId;
    }

    /* Based on https://github.com/rayzhangcl/ESDemo and https://github.com/joshua2ua/AndroidElasticSearch */
    public void run() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");

        try {
            HttpResponse response = httpClient.execute(httpDelete);
            String status = response.getStatusLine().toString();
            Log.i("NetworkDataManager", status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
