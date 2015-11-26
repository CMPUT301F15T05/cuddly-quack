package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import ca.ualberta.cs.swapmyride.Controller.NetworkDataManager;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
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
        user.setUserEmail("gbullock@ualberta.ca");

        ndm.saveUser(user);
    }

    public void testDeleteUser(){
        NetworkDataManager ndm = new NetworkDataManager();
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualberta.ca");

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

    public void testRetrieveUser(){
        NetworkDataManager ndm = new NetworkDataManager();
        User user = new User();
        User user2;
        user.setName("Connor");
        user.setUserAddress("1021");
        user.setUserName("ccdunn");
        user.setUserEmail("ccdunn@ualberta.ca");
        user.addFriend("gbullock");

        ndm.saveUser(user);

        Log.i("NetworkDataManager", "Saved User!");

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }

        user2 = ndm.retrieveUser(user.getUserName());

        assertTrue("Users are not equal!!", user.equals(user2));

        Log.i("NetworkDataManager", "Users Equal!");
        //wait a decent amount of time to ensure the save has time to happen
        try{
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }

        ndm.deleteUser(user.getUserName());

        Log.i("NetworkDataManager", "Deleted User!");
    }
}
