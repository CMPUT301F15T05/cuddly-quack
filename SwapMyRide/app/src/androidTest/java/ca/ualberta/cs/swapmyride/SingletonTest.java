package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class SingletonTest extends ActivityInstrumentationTestCase2 {
    public SingletonTest(){
        super(MainMenu.class);
    }

    public void testAddUser(){
        User user1 = new User();
        user1.setUserName("adrianomarini");
        user1.setUserEmail("marini@ualberta.ca");
        Singleton.addUser(user1);
        User testUser = Singleton.getCurrentUser();
        assertTrue(testUser.equals(user1));
    }



}
