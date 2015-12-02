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

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.SearchHit;

/**
 * Given a photoId and a url the photo is searched for on the server
 *
 * Created by Garry on 2015-11-29.
 */
public class SearchPhotoRunnable implements Runnable {
    Gson gson = new Gson();
    private String url;
    private boolean exists;

    public SearchPhotoRunnable(String photoId, String url) {
        //set up the query to get just the the necessary info... no source information
        //"Source Filtering" - https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
        //Nov 26, 2015
        this.url = url + "photos/" + photoId + "?_source=false";
    }

    public void run() {
        HttpClient client = new DefaultHttpClient();
        HttpGet search = new HttpGet(url);
        search.setHeader("Accept", "application/json");
        HttpResponse response = null;
        SearchHit<Photo> hit;

        try {
            response = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type searchHitType = new TypeToken<SearchHit<Photo>>() {
        }.getType();
        try {
            hit = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchHitType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        exists = hit.isFound();
    }

    public boolean getExists() {
        return exists;
    }
}
