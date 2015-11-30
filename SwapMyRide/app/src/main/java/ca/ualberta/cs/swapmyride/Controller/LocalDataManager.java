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
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ca.ualberta.cs.swapmyride.Misc.DefaultPhotoSingleton;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.User;

/**
 * Data manager is designed to ensure that all serializing, loading, and saving of
 * data in the app.
 *
 * @author Garry Bullock on 2015-11-02
 */
public class LocalDataManager {
    private String userFilePath = "";
    private String photoFilePath = "";
    private Gson gson = new Gson();
    private FileOutputStream outputStream;
    private Context context;

    public LocalDataManager(Context context){
        this.context = context;

    }

    /**
     * Save user converts the given user object into a Json string, and then attempts
     * to write it to internal disk space. The user can the be found under the unique filename
     * that is the same as their username.
     *
     * http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson
     *
     * @param user user to write to internal disk
     * @see <a href="http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson">stackOverflow</a>
     * @param user
     */
    public void saveUser(final User user){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userJson = gson.toJson(user);
                try{
                    //Log.i("USERFILEPATH",userFilePath+user.getUserName());
                    Log.i("NEWDATAMANAGER", "INSIDE LOCAL SAVE - USERNAME: "+ user.getUserName());
                    outputStream = context.openFileOutput(userFilePath + user.getUserName(),
                            Context.MODE_PRIVATE);
                    outputStream.write(userJson.getBytes());
                    outputStream.close();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    throw new RuntimeException();
                }
            }
        });

        thread.start();

    }

    /**
     * Load user reads a user from internal disk, using the user name given as a unique file name.
     * @param userName check to see it exists before using (ie. searchUser(userName))
     * @return the loaded User
     *
     * http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson
     *
     * Edited from user giampaolo on stackOverflow. Accessed 1 November 2015.
     *
     * @see <a href="http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson">stackOverflow</a>
     */
    public User loadUser(String userName){
        User user;
        try {
            FileInputStream fis = context.openFileInput(userFilePath + userName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            Gson gson = new Gson();
            user = gson.fromJson(json, User.class);
        }
        catch (FileNotFoundException e){
            user = null;
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        Log.i("NEWDATAMANAGER", "INSIDE LOCAL LOAD - USERNAME: " + user.getUserName());
        return user;
    }

    /**
     * deleteUser deletes the serialized data for a user that is located in the
     * database.
     *
     * http://stackoverflow.com/questions/14737996/android-deleting-a-file-from-internal-storage
     *
     * Edited from user hardartcore on stackOverflow. Accessed 1 November 2015.
     *
     * @see <a href="http://stackoverflow.com/questions/14737996/android-deleting-a-file-from-internal-storage">stackOverflow</a>
     * @param username
     */

    public void deleteUser(String username){
        File dir = context.getFilesDir();
        File file = new File(dir, userFilePath + username);
        file.delete();
    }

    /**
     * Takes in a username and searches through the database to see if the
     * user exists. If the user exists, it returns true. Else, returns false.
     *
     * @return True or False
     * @param username
     */
    public boolean searchUser(String username) {
        if(username.equals("")) return false;
        return context.getFileStreamPath(userFilePath + username).exists();
    }


    public void savePhoto(final Photo photo){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String photoJson = gson.toJson(photo);
                try{
                    //Log.i("USERFILEPATH",userFilePath+user.getUserName());
                    Log.i("NEWDATAMANAGER", "INSIDE LOCAL SAVE - Photo: "+ photo.getUid().getID());
                    outputStream = context.openFileOutput(photoFilePath + photo.getUid().getID(),
                            Context.MODE_PRIVATE);
                    outputStream.write(photoJson.getBytes());
                    outputStream.close();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
                catch (IOException e){
                    throw new RuntimeException();
                }
            }
        });

        thread.start();

    }

    public Photo loadPhoto(String photoId){
        Photo photo;
        try {
            FileInputStream fis = context.openFileInput(photoFilePath + photoId);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String json = sb.toString();
            Gson gson = new Gson();
            photo = gson.fromJson(json, Photo.class);
        }
        catch (FileNotFoundException e){
            photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        Log.i("NEWDATAMANAGER", "INSIDE LOCAL LOAD - PHOTO: " + photoId);
        return photo;
    }

    public void deletePhoto(String photoId){
        File dir = context.getFilesDir();
        File file = new File(dir, photoFilePath + photoId);
        file.delete();
    }

    public boolean searchPhoto(String photoId) {
        if(photoId.equals("")) return false;
        return context.getFileStreamPath(photoFilePath + photoId).exists();
    }
}
