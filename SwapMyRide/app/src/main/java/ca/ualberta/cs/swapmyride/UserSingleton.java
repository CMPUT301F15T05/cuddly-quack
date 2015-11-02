package ca.ualberta.cs.swapmyride;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingleton {
    private static User currentUser;
    private static ArrayList<User> users = new ArrayList<>();

    private static UserSingleton ourInstance = new UserSingleton();

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    private UserSingleton() {
    }

    public static void addCurrentUser(String username){
        String tempString;
        for(User user : users) {
            tempString = user.getName();
            if (tempString.equals(username)) {
                currentUser = user;
            }
        }
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static boolean userExists(String username){
        String tempString;
        for(User user : users){
            tempString = user.getUserName();
            Log.i("userExits", tempString);
            if(tempString.equals(username)){
                return true;
            }
        }
        return false;
    }

}
