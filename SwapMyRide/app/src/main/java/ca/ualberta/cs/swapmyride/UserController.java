package ca.ualberta.cs.swapmyride;

import java.util.ArrayList;

/**
 * Created by Garry on 2015-11-01.
 */
public class UserController {

    //for the time being, these classes access the local user list to verify
    //TODO: implement remote database usage for userExists, addCurrentUser, addUser
    public boolean userExists(String username){
        return true;
    }

    //adds current user to the contextual variables for the usage
    public void addCurrentUser(String username){
        String tempString;
        User currentUser = new User();
        for(User user : UserSingleton.getUsers()) {
            tempString = user.getUserName();
            if (tempString.equals(username)) {
                currentUser = user;
            }
        }
        UserSingleton.addCurrentUser(currentUser);
    }

    //adds user to user list
    public boolean addUser(User user){
        UserSingleton.addUser(user);
        return true;
    }

    public User getCurrentUser(){
        return UserSingleton.getCurrentUser();
    }

    public InventoryList getInventory(User user){
        //TODO: Get the inventory of a given user
        InventoryList inventory;
        User currentUser = getCurrentUser();
        inventory = currentUser.getInventory();
        return inventory;
    }

    public void changeDownloadPreferance(boolean preference){
        //TODO:Switch the prefrance of the current user
        UserSingleton.getCurrentUser().setDownloadImages(preference);
    }

    public FriendsList getFriends(User user){
        return user.getFriends();
    }

    public void addFriend(User toAdd){
        UserSingleton.getCurrentUser().addFriend(toAdd);
    }

    public void deleteFriend(User toDelete){
        UserSingleton.getCurrentUser().removeFriend(toDelete);
    }

}
