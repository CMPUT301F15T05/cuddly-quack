package ca.ualberta.cs.swapmyride;

import android.content.Context;

/**
 * Created by Garry on 2015-11-01.
 */
public class UserController {

    Context context;

    public UserController(Context thisContext) {
        context = thisContext;
    }

    //for the time being, these classes access the local user list to verify
    //TODO: implement remote database usage for setCurrentUser, addUser
    //adds current user to the contextual variables for the usage
    public void addCurrentUser(String username){
        DataManager dataManager = new DataManager(context);
        User currentUser = dataManager.loadUser(username);
        UserSingleton.setCurrentUser(currentUser);
    }

    //adds user to user list
    public boolean addUser(User user){
        DataManager dataManager = new DataManager(context);
        dataManager.saveUser(user);
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
