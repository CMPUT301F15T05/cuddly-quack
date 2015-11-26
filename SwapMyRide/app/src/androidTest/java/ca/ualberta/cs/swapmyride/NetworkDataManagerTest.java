package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import ca.ualberta.cs.swapmyride.Controller.NetworkDataManager;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Garry on 2015-11-25.
 */
public class NetworkDataManagerTest extends ActivityInstrumentationTestCase2 {
    public NetworkDataManagerTest(){super(MainMenu.class);}

    public void testSaveUser(){
        NetworkDataManager ndm = new NetworkDataManager();
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");

        ndm.saveUser(user);
    }
    
    public void testDeleteUser(){
        NetworkDataManager ndm = new NetworkDataManager();
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");

        ndm.saveUser(user);

        Log.i("NetworkDataManager", "Saved User!");


        //wait a decent amount of time to ensure the save has time to happen
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        ndm.deleteUser(user.getUserName());

        Log.i("NetworkDataManager", "Deleted User!");
    }
}
