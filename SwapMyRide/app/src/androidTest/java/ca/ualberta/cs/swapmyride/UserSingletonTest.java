package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingletonTest extends ActivityInstrumentationTestCase2 {
    public UserSingletonTest(){super(MainMenu.class);
    }

    public void testAddUser(){
        User user1 = new User();
        user1.setUserName("adrianomarini");
        user1.setUserEmail("marini@ualberta.ca");
        UserSingleton.setCurrentUser(user1);
        User testUser = UserSingleton.getCurrentUser();
        assertTrue(testUser.equals(user1));
    }
}
