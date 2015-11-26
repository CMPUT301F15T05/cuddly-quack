package ca.ualberta.cs.swapmyride.Misc;

import ca.ualberta.cs.swapmyride.Model.User;


/**
 * Created by Garry on 2015-11-25.
 */
public class SaveUserRunnable implements Runnable{
    private User user;
    private String url;
    public SaveUserRunnable(final User user, final String url){
        this.user = user;
        this.url = url + "users/" + user.getUserName();
    }

    public  void run(){
    }
}