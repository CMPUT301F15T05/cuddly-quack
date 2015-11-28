package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import ca.ualberta.cs.swapmyride.Model.User;

/**
 * Created by Garry on 2015-11-25.
 */
public class DeleteUserRunnable implements Runnable {
    private String url;

    public DeleteUserRunnable(String username, String url){
        this.url = url +  "users/" + username;
    }

    /* Based on https://github.com/rayzhangcl/ESDemo and https://github.com/joshua2ua/AndroidElasticSearch */
    public void run(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpDelete httpDelete = new HttpDelete(url);
        httpDelete.setHeader("Accept", "application/json");

        try{
            HttpResponse response = httpClient.execute(httpDelete);
            String status = response.getStatusLine().toString();
            Log.i("NetworkDataManager", status);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
