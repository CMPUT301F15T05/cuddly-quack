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

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingletonTest extends ActivityInstrumentationTestCase2 {
    public UserSingletonTest() {
        super(MainMenu.class);
    }

    public void testAddUser() {
        User user1 = new User();
        user1.setUserName("adrianomarini");
        user1.setUserEmail("marini@ualberta.ca");
        UserSingleton.addCurrentUser(user1);
        User testUser = UserSingleton.getCurrentUser();
        assertTrue(testUser.equals(user1));
    }
}
