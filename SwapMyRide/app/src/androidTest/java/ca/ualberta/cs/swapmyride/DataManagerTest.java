package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

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

        assertFalse(getActivity().getBaseContext().getFileStreamPath("user/"+user.getName()).exists());

        dataManager.saveUser(user);

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.equals(user));
    }
}
