package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

public class ConfigurationTest extends ActivityInstrumentationTestCase2{
    public ConfigurationTest(){
        super(MainMenu.class);
    }

    // Use Case 31: Download Image Preference
    //public void testImagePreference(){
        //User user = new User();
        //user.setDownloadImages(false);
        //Image image = new Image();
        //autoDownloadImage should return true if the image downloads
        //boolean downloaded = autoDownloadImage(image);
        //assertFalse(downloaded);
        //when the user selects to download an image, it should download
        //downloaded = manualDownloadImage(image);
        //assertTrue(downloaded);
   // }

    // Use Case 32: Edit Profile
    public void testEditProfile(){
        User user = new User();
        user.setName("gbullock");
        user.setUserName("Garry");
        user.setUserEmail("gbull@whatever.com");
        user.setUserAddress("123 123st");

        //check all user info was set properly
        assertTrue(user.getUserName().equals("Garry"));
        assertTrue(user.getUserEmail().equals("gbull@whatever.com"));
        assertTrue(user.getUserAddress().equals("123 123st"));

        String newName = "Jake";
        String newEmail = "Jake@whatever.com";
        String newAddr = "111 101st";

        user.setName(newName);
        user.setUserEmail(newEmail);
        user.setUserAddress(newAddr);

        //check overwriting previous works properly
        assertTrue(user.getName().equals(newName));
        assertTrue(user.getUserEmail().equals(newEmail));
        assertTrue(user.getUserAddress().equals(newAddr));
    }

}
