package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * Created by Garry on 2015-11-02.
 */
public class DataManagerTest extends ActivityInstrumentationTestCase2 {
    public DataManagerTest(){super(MainMenu.class);}

    public void testSaveUser(){
        DataManager dataManager = new DataManager(getActivity());
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());

        dataManager.saveUser(user);

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.getName().equals(user.getName()));
        assertTrue(loadTo.getUserEmail().equals(user.getUserEmail()));
        assertTrue(loadTo.getUserAddress().equals(user.getUserAddress()));
        assertTrue(loadTo.getUserName().equals(user.getUserName()));
    }

    
}
