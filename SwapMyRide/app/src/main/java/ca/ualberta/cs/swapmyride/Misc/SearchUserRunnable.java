package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

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

import ca.ualberta.cs.swapmyride.Model.SearchHit;
import ca.ualberta.cs.swapmyride.Model.User;

/**
 * Created by Garry on 2015-11-26.
 */
public class SearchUserRunnable implements Runnable {
    Gson gson = new Gson();
    private String url;
    private boolean exists;

    public SearchUserRunnable(String username, String url) {
        //set up the query to get just the the necessary info... no source information
        //"Source Filtering" - https://www.elastic.co/guide/en/elasticsearch/reference/current/docs-get.html
        //Nov 26, 2015
        this.url = url + "users/" + username + "?_source=false";
    }

    public void run() {
        HttpClient client = new DefaultHttpClient();
        HttpGet search = new HttpGet(url);
        search.setHeader("Accept", "application/json");
        HttpResponse response = null;
        SearchHit<User> hit;

        try {
            response = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type searchHitType = new TypeToken<SearchHit<User>>() {
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

        Log.i("ISFOUND", String.valueOf(hit.isFound()));
        exists = hit.isFound();
    }

    public boolean getExists() {
        return exists;
    }
}
