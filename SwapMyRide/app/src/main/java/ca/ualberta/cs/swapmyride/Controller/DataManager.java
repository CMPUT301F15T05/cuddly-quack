package ca.ualberta.cs.swapmyride.Controller;

import android.content.Context;

import ca.ualberta.cs.swapmyride.Model.User;

/**
 * An abstract class that manages the saving, loading, searching, and retrieving of data, from both
 * the server, and local disk.
 *
 *
 */
public class DataManager {
    private Context context;

    public DataManager(Context context){
        this.context = context;
    }

    public void saveUser(User user){

    }

    public User loadUser(String userName){
        return new User();
    }

    public void deleteUser(String username){

    }

    public boolean searchUser(String username){
        return true;
    }

    /**
     * updateFriends gets friends from the server based on the friends list
     * of a user. This exists so that a friendsList does not store user
     * obejcts - but just usernames so that it is smaller.
     *
     * @param user
     */
    public void updateFriends(User user){
        for (String friendUserName : user.getFriends().getFriendList()) {
            if(searchUser(friendUserName)){
                //TODO Update the friends from server
            }
            else{
                //TODO Code to load friends that do not exist in the local memory
            }
        }
    }
}
