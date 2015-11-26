package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

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
}
