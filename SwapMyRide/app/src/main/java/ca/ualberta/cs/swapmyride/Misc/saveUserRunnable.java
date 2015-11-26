package ca.ualberta.cs.swapmyride.Misc;

import android.util.Log;

import ca.ualberta.cs.swapmyride.Model.User;
import org.apache.http.HttpEntity;

/**
 * Created by Garry on 2015-11-25.
 */
public class SaveUserRunnable implements Runnable{
    private User user;
    private String url;
    //private
    public SaveUserRunnable(final User user, final String url){
        this.user = user;
        this.url = url + "users/" + user.getUserName();
        Log.d("USER URL", this.url);
    }

    public  void run(){
    }
}
