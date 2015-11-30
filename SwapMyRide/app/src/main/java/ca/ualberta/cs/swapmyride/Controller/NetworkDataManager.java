package ca.ualberta.cs.swapmyride.Controller;

import android.util.Log;

import ca.ualberta.cs.swapmyride.Misc.DeletePhotoRunnable;
import ca.ualberta.cs.swapmyride.Misc.DeleteUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.RetrievePhotoRunnable;
import ca.ualberta.cs.swapmyride.Misc.RetrieveUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.SavePhotoRunnable;
import ca.ualberta.cs.swapmyride.Misc.SaveUserRunnable;
import ca.ualberta.cs.swapmyride.Misc.SearchPhotoRunnable;
import ca.ualberta.cs.swapmyride.Misc.SearchUserRunnable;
import ca.ualberta.cs.swapmyride.Model.Photo;
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
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK SAVE USER - " + user.getUserName());
        Thread saveUserThread = new Thread(new SaveUserRunnable(user, hostUrl));
        saveUserThread.start();
    }

    public void deleteUser(String username){
        Thread deleteUserThread = new Thread(new DeleteUserRunnable(username, hostUrl));
        deleteUserThread.start();
    }

    public User retrieveUser(String username){
        User user;
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK LOAD USER! - " + username);
        RetrieveUserRunnable runnable = new RetrieveUserRunnable(username, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        user = runnable.getUser();
        return user;
    }

    public boolean searchUser(String username){
        SearchUserRunnable searchUserRunnable = new SearchUserRunnable(username, hostUrl);
        Thread thread = new Thread(searchUserRunnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return searchUserRunnable.getExists();
    }

    public void savePhoto(Photo photo){
        Thread thread = new Thread(new SavePhotoRunnable(photo, hostUrl));
        thread.start();
    }

    public Photo retrievePhoto(String photoId){
        RetrievePhotoRunnable runnable = new RetrievePhotoRunnable(photoId, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        Photo photo = runnable.getPhoto();
        return photo;
    }

    public void deletePhoto(String photoId){
        Thread thread = new Thread(new DeletePhotoRunnable(photoId, hostUrl));
        thread.start();
    }

    public boolean searchPhoto(String photoId){
        SearchPhotoRunnable runnable = new SearchPhotoRunnable(photoId,hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return runnable.getExists();
    }
}

