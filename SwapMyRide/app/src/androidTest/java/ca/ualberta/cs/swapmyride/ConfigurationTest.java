package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Garry on 2015-10-08.
 */
public class ConfigurationTest extends ActivityInstrumentationTestCase2{
    public ConfigurationTest(){
        super(MainMenu.class);
    }

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

    public void testEditProfile(){
        
    }

}
