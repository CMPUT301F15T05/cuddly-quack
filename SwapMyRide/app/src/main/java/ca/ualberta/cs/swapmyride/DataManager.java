package ca.ualberta.cs.swapmyride;


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

/**
 * Created by Garry on 2015-11-02.
 */
public class DataManager {
    private String userFilePath = "";
    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = new Gson();
    private FileOutputStream outputStream;
    private Context context;

    public DataManager(Context context){
        this.context = context;

    }


    //edited from user 'giampaolo'
    //http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson
    //Nov. 1/2015
    public void saveUser(User user){

        String userJson = gson.toJson(user);
        try{
            Log.i("USERFILEPATH",userFilePath+user.getUserName());
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

    //edited from user 'giampaolo'
    //http://stackoverflow.com/questions/19459082/read-and-write-data-with-gson
    //Nov. 1/2015
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
            throw new RuntimeException();
        }

        return user;
    }

    //edited from user 'hardartcore'
    //http://stackoverflow.com/questions/14737996/android-deleting-a-file-from-internal-storage
    //Nov 1, 2015
    public void deleteUser(String username){
        File dir = context.getFilesDir();
        File file = new File(dir, username);
        file.delete();
    }

    /*Checks to see if a user exists. If it does, it will pass the user back, else it will
    pass back a user equal to null
     */
    public boolean searchUser(String username) {
        if(username.equals("")) return false;
        return context.getFileStreamPath(userFilePath + username).exists();
    }
}
