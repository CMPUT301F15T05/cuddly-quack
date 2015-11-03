package ca.ualberta.cs.swapmyride;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingleton {
    private static User currentUser = null;
    private static ArrayList<User> users = new ArrayList<>();

    private static UserSingleton ourInstance = new UserSingleton();

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    private UserSingleton() {
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        //TODO temporary fix, a user should always be created through login
        if(currentUser == null){
            return new User();
        }
        return currentUser;
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static ArrayList<User> getUsers(){
        return users;
    }

}
