/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.View.MainMenu;

public class ConfigurationTest extends ActivityInstrumentationTestCase2 {
    public ConfigurationTest() {
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
    public void testEditProfile() {
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
