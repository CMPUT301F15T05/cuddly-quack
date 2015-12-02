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

/**
 * Data manager is designed to ensure that all serializing, loading, and saving of
 * data is done properly to the elastic search server
 *
 * @author Garry Bullock on 2015-11-02
 */
public class NetworkDataManager {

    final static String hostUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t05testing/";

    public NetworkDataManager() {
    }

    /**
     * Method for saving users to the server.
     *
     * @param User that is to be saved
     */
    public void saveUser(User user) {
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK SAVE USER - " + user.getUserName());
        Thread saveUserThread = new Thread(new SaveUserRunnable(user, hostUrl));
        saveUserThread.start();
    }

    /**
     * Method for deleting users from the server
     *
     * @param  username string to be deleted
     */

    public void deleteUser(String username) {
        Thread deleteUserThread = new Thread(new DeleteUserRunnable(username, hostUrl));
        deleteUserThread.start();
    }

    /**
     * Method for getting users from the server.
     *
     * @param String of username to be retrieved
     */

    public User retrieveUser(String username) {
        User user;
        Log.i("NEWDATAMANAGER", "INSIDE NETWORK LOAD USER! - " + username);
        RetrieveUserRunnable runnable = new RetrieveUserRunnable(username, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user = runnable.getUser();
        return user;
    }

    /**
     * Method for searching users on the server
     *
     * @param String of username to be searched
     */

    public boolean searchUser(String username) {
        SearchUserRunnable searchUserRunnable = new SearchUserRunnable(username, hostUrl);
        Thread thread = new Thread(searchUserRunnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return searchUserRunnable.getExists();
    }

    /**
     * Method to save photo to the server
     *
     * @param photo
     */

    public void savePhoto(Photo photo) {
        Thread thread = new Thread(new SavePhotoRunnable(photo, hostUrl));
        thread.start();
    }

    /**
     *
     * Method to retrieve a photo from the server based on it's UniqueID
     *
     * @param photoId
     * @return the photo that was retrieved.
     */

    public Photo retrievePhoto(String photoId) {
        RetrievePhotoRunnable runnable = new RetrievePhotoRunnable(photoId, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Photo photo = runnable.getPhoto();
        return photo;
    }

    /**
     * Method for deleting photo from the server
     *
     * @param String of the photoID of the photo to be deleted.
     */

    public void deletePhoto(String photoId) {
        Thread thread = new Thread(new DeletePhotoRunnable(photoId, hostUrl));
        thread.start();
    }

    /**
     *
     * Method to search for a specific photo ID on the server
     *
     * @param photoId in question
     * @return boolean if it exists.
     */

    public boolean searchPhoto(String photoId) {
        SearchPhotoRunnable runnable = new SearchPhotoRunnable(photoId, hostUrl);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return runnable.getExists();
    }
}

