package ca.ualberta.cs.swapmyride;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingleton {
    private static User currentUser;

    private static UserSingleton ourInstance = new UserSingleton();

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    private UserSingleton() {
    }

    public static void addUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

}
