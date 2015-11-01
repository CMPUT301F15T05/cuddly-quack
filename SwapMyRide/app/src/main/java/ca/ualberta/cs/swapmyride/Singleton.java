package ca.ualberta.cs.swapmyride;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class Singleton {
    private static User currentUser;

    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public static void addUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        return currentUser;
    }

}
