package ca.ualberta.cs.swapmyride.Controller;

import ca.ualberta.cs.swapmyride.Misc.SaveUserRunnable;
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
        Thread saveUserThread = new Thread(new SaveUserRunnable(user,hostUrl));
        saveUserThread.start();
    }

    public void deleteUser(String username){

    }
}

