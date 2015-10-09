package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Garry on 2015-10-08.
 */
public class ConfigurationTest extends ActivityInstrumentationTestCase2{
    public ConfigurationTest(){
        super(MainMenu.class);
    }

    // Use Case 31: Download Image Preference
    public void testImagePreferance(){
        User user = new User();
        user.setDownloadImages(false);
        Image image = new Image();
        //autoDownloadImage should return true if the image downloads
        boolean downloaded = autoDownloadImage(image);
        assertFalse(downloaded);
        //when the user selects to download an image, it should download
        downloaded = manualDownloadImage(image);
        assertTrue(downloaded);
    }

    // Use Case 32: Edit Profile
    public void testEditProfile(){
        User user = new User();
        user.addUser("gbullock");
        user.addUserName("Garry");
        user.addUserEmail("gbull@whatever.com");
        user.addUserAddress("123 123st");

        //check all user info was set properly
        assertTrue(user.getName().equals("Garry"));
        assertTrue(user.getEmail().equals("gbull@whatever.com"));
        assertTrue(user.getAddress().equals("123 123st"));

        user.AddUser("Jake");
        user.addUserEmail("Jake@whatever.com");
        user.addUserAddress("111 101st");

        //check overwriting previous works properly
        assertTrue(user.getName().equals("Jake"));
        assertTrue(user.getEmail().equals("Jake@whatever.com"));
        assertTrue(user.getAddress().equals("111 101st"));
    }

}
