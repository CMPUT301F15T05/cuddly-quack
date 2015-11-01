package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-11-01.
 */
public class UserController {
    //private User user;

    public boolean validUserName(String userName){
        //TODO: Check server to see if the user exists

        return true;
    }

    public boolean addUser(User user){
        //TODO: Add the given user to the server

        return true;
    }

    public InventoryList getInventory(User user){
        //TODO: Get the inventory of a given user
        InventoryList inventory = new InventoryList();

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
