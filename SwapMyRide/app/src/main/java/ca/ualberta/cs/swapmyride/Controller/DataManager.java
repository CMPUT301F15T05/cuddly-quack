package ca.ualberta.cs.swapmyride.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ca.ualberta.cs.swapmyride.Model.User;

/**
 * An abstract class that manages the saving, loading, searching, and retrieving of data, from both
 * the server, and local disk.
 *
 *
 */
public class DataManager {
    private Context context;
    NetworkDataManager ndm;
    LocalDataManager ldm;

    public DataManager(Context context){
        this.context = context;
        ndm = new NetworkDataManager();
        ldm = new LocalDataManager(context);
    }

    public void saveUser(User user){
        if(networkAvailable()){
            //SAVE THE USER ON SERVER
            ndm.saveUser(user);
        }
        //save user on disk
        ldm.saveUser(user);
    }

    /**
     * Loads the user from network if it is available, else it will attempt to load the user from
     * disk...
     * Returns a new user if it the given userName does not exist
     * @param username
     * @return
     */
    public User loadUser(String username){
        if(networkAvailable()){
            if (searchUserServer(username)){
                return ndm.retrieveUser(username);
            }
        }
        else if (ldm.searchUser(username)){
            return ldm.loadUser(username);
        }

        return new User();
    }

    public void deleteUser(String username){
        if(networkAvailable()){
            ndm.deleteUser(username);
        }
        ldm.deleteUser(username);
    }

    public boolean searchUserLocal(String username){
        return ldm.searchUser(username);
    }

    public boolean searchUserServer(String username){
        if(networkAvailable()){
            return ndm.searchUser(username);
        }
        else return false;
    }
    /**
     * updateFriends gets friends from the server based on the friends list
     * of a user. This exists so that a friendsList does not store user
     * obejcts - but just usernames so that it is smaller.
     * @param user
     */
    public void updateFriends(User user){
        for (String friendUserName : user.getFriends().getFriendList()) {
            if(searchUserServer(friendUserName)){
                //TODO Update the friends from server
            }
            else{
                //TODO Code to load friends that do not exist in the local memory
            }
        }
    }

    /**
     * networkAvailable checks the android device to see if there is a network connection
     * available. It should be performed before doing any network action.
     * @return
     *
     * Taken from StackOverflow "Detect whether there is an Internet connection available on Android"
     * Solution from user Alexandre Jasmine
     * http://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
     * November 27/2015
     */
    private boolean networkAvailable(){
        ConnectivityManager connectionManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectionManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
