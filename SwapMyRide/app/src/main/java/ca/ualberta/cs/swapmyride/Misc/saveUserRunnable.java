package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

import com.google.gson.Gson;

import ca.ualberta.cs.swapmyride.Model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by Garry on 2015-11-25.
 */
public class SaveUserRunnable implements Runnable{
    private User user;
    private String url;
    private Gson gson = new Gson();
    //private
    public SaveUserRunnable(final User user, final String url){
        this.user = user;
        this.url = url + "users/" + user.getUserName();
        Log.d("USER URL", this.url);
    }
    /* Based on https://github.com/rayzhangcl/ESDemo and https://github.com/joshua2ua/AndroidElasticSearch */
    public  void run(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpAdd = new HttpPost(url);
        HttpResponse response;
        StringEntity stringEntity = null;

        try {
            stringEntity = new StringEntity(gson.toJson(user));
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        httpAdd.setEntity(stringEntity);
        httpAdd.setHeader("Accept", "application/json");

        //possible IO Error and an HTTP apache error
        try {
            response = httpClient.execute(httpAdd);
            String status = response.getStatusLine().toString();
            Log.i("SaveUserRunnable-Status", status);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
