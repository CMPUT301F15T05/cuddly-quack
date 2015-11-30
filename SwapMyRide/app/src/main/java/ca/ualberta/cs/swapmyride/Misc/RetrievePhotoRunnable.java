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
 * Created by Garry on 2015-11-29.
 */
public class RetrievePhotoRunnable implements Runnable {
    private Photo photo;
    private String url;
    private Gson gson = new Gson();

    public RetrievePhotoRunnable(String photoId, String url){
        this.url = url + "photos/" + photoId;
    }
    /*
     * Returns the photo if found by the retrieve function. Make sure to wait for the thread to
     * Finish first.
     */
    public Photo getPhoto(){
        return this.photo;
    }

    public void run(){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = null;

        SearchHit<Photo> hit = null;
        try{
            response = httpClient.execute(httpGet);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        Type searchHitType = new TypeToken<SearchHit<Photo>>(){}.getType();

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

        this.photo = hit.getSource();
    }

}
