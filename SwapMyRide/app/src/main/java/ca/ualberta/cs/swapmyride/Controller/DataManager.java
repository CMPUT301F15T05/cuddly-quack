/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package ca.ualberta.cs.swapmyride.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UniqueID;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;

/**
 * An abstract class that manages the saving, loading, searching, and retrieving of data, from both
 * the server, and local disk.
 *
 * @author Garry Bullock
 */
public class DataManager {
    NetworkDataManager ndm;
    LocalDataManager ldm;
    private Context context;

    public DataManager(Context context) {
        this.context = context;
        ndm = new NetworkDataManager();
        ldm = new LocalDataManager(context);
    }

    public void saveUser(User user) {
        //save user on disk
        ldm.saveUser(user);

        if (networkAvailable()) {
            //SAVE THE USER ON SERVER
            ndm.saveUser(user);
        }
    }

    /**
     * Loads the user from network if it is available, else it will attempt to load the user from
     * disk...
     * Returns a new user if it the given userName does not exist
     *
     * @param username
     * @return
     */
    public User loadUser(String username) {
        /*
        if(networkAvailable()){
            if (searchUserServer(username)){
                return ndm.retrieveUser(username);
            }
        }
        */
        if (searchUserLocal(username)) {
            return ldm.loadUser(username);
        }

        Log.i("NEWDATAMANAGER", "Returning a new user...");
        return new User();
    }

    public void deleteUser(String username) {

        if (networkAvailable()) {
            ndm.deleteUser(username);
        }
        ldm.deleteUser(username);
    }

    public void deletePhoto(String photoId) {
        if (networkAvailable()) {
            ndm.deletePhoto(photoId);
        }
        ldm.deletePhoto(photoId);
    }

    public boolean searchUserLocal(String username) {
        return ldm.searchUser(username);
    }

    public boolean searchUserServer(String username) {
        if (networkAvailable()) {
            return ndm.searchUser(username);
        } else return false;

    }

    public boolean searchPhotoLocal(String photoId) {
        return ldm.searchPhoto(photoId);
    }

    public boolean searchPhotoServer(String photoId) {
        if (networkAvailable()) {
            return ndm.searchPhoto(photoId);
        } else return false;

    }

    public void savePhoto(Photo photo) {
        if (networkAvailable()) {
            ndm.savePhoto(photo);
        }
        ldm.savePhoto(photo);
    }

    /**
     * updateFriends gets friends from the server based on the friends list
     * of a user. This exists so that a friendsList does not store user
     * objects - but just usernames so that it is smaller.
     */
    public void updateFriends() {
        User friend;
        //empty the user list
        UserSingleton.setFriends(new ArrayList<User>());

        for (String friendUserName : UserSingleton.getCurrentUser().getFriends().getFriendList()) {
            if (searchUserServer(friendUserName)) {
                friend = ndm.retrieveUser(friendUserName);
                ldm.saveUser(friend);
                //if we want to download photos, do it here
                if (UserSingleton.getDownloadPhotos()) {
                    //get all vehicles from the friend
                    for (Vehicle vehicle : friend.getInventory().getList()) {
                        //get all the unique id's for photos for each vehicle
                        for (UniqueID id : vehicle.getPhotoIds()) {
                            //if they already exist, we dont need to download them
                            if (!ldm.searchPhoto(id.getID())) {
                                ldm.savePhoto(ndm.retrievePhoto(id.getID()));
                            }
                        }
                    }
                }
            } else {
                friend = ldm.loadUser(friendUserName);
            }
            UserSingleton.addFriends(friend);
        }
    }

    /**
     * networkAvailable checks the android device to see if there is a network connection
     * available. It should be performed before doing any network action.
     *
     * @return Taken from StackOverflow "Detect whether there is an Internet connection available on Android"
     * Solution from user Alexandre Jasmine
     * http://stackoverflow.com/questions/4238921/detect-whether-there-is-an-internet-connection-available-on-android
     * November 27/2015
     */
    public boolean networkAvailable() {
        ConnectivityManager connectionManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectionManager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
