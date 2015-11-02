package ca.ualberta.cs.swapmyride;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
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
    private Gson gson = new Gson();
    private FileOutputStream outputStream;
    private Context context;

    public DataManager(Context context){
        this.context = context;
    }

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
}
