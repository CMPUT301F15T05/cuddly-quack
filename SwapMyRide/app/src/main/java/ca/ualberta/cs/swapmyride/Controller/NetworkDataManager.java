package ca.ualberta.cs.swapmyride.Controller;

import android.util.Log;

import ca.ualberta.cs.swapmyride.Misc.DeleteUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.RetrieveUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.SaveUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.SearchUserRunnable;
import ca.ualberta.cs.swapmyride.Model.User;

/*
 * A class for managing the server connection
 */
public class NetworkDataManager {

    final static String hostUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t05testing/";

    public NetworkDataManager(){
    }
    /*
     * Method for saving users to the server.
     */
    public void saveUser(User user){
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK SAVE USER - " + user.getUserName());
        Thread saveUserThread = new Thread(new SaveUserRunnable(user, hostUrl));
        saveUserThread.start();
    }

    public void deleteUser(String username){
        Thread deleteUserThread = new Thread(new DeleteUserRunnable(username, hostUrl));
        deleteUserThread.start();
    }

    public User retrieveUser(String username){
        User user;
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK LOAD USER! - " + username);
        RetrieveUserRunnable runnable = new RetrieveUserRunnable(username, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        user = runnable.getUser();
        return user;
    }

    public boolean searchUser(String username){
        SearchUserRunnable searchUserRunnable = new SearchUserRunnable(username, hostUrl);
        Thread thread = new Thread(searchUserRunnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return searchUserRunnable.getExists();
    }
}

