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

import ca.ualberta.cs.swapmyride.Model.FriendsList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Daniel on 2015-10-08.
 */
public class FriendsTest  extends ActivityInstrumentationTestCase2 {
    public FriendsTest(){
        super(MainMenu.class);
    }

    // Use Case 8: Find User
    public void testFindUser() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.setName("camclean");
        usertwo.setName("ccdunn");

        friendsList.addFriend(userone.getUserName());
        friendsList.addFriend(usertwo.getUserName());

        // Storing the returned User class in variable found
        Boolean found = friendsList.findUser(userone.getUserName());
        // Check if found is equal to what findUser gets
        assertTrue(found);
    }

    // Use Case 9: Add Friend
    public void testAddFriend() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.setName("camclean");
        usertwo.setName("ccdunn");

        friendsList.addFriend(userone.getUserName());
        friendsList.addFriend(usertwo.getUserName());

        // Making sure that friendslist has camclean
        assertTrue(friendsList.hasUser(userone));
    }

    // Use Case 10: Remove Friend
    public void testRemoveFriend() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.setName("camclean");
        usertwo.setName("ccdunn");

        friendsList.addFriend(userone.getUserName());
        friendsList.addFriend(usertwo.getUserName());

        friendsList.removeFriend(userone.getUserName());

        // Making sure that friendslist has camclean
        assertFalse(friendsList.hasUser(userone));
    }

    // Use Case 11: User Profile
    public void testUserProfile() {
        User user = new User();
        user.setName("camclean");
        user.setUserName("Carson Mclean");
        user.setUserEmail("camclean@ualberta.ca");
        user.setUserAddress("300 Shirley Street");

        // Making sure that user has these attributes
        assertTrue(user.getUserName().equals("Carson Mclean"));
        assertTrue(user.getUserEmail().equals("camclean@ualberta.ca"));
        assertTrue(user.getUserAddress().equals("300 Shirley Street"));
    }

    // Use Case 12: View Profiles
    public void testViewProfiles() {
        FriendsList friendsList = new FriendsList();

        User user = new User();

        user.setName("camclean");
        user.setUserName("Carson Mclean");

        friendsList.addFriend(user.getUserName());

        // Storing the returned User class in variable found
        Boolean found = friendsList.findUser(user.getUserName());
        assertTrue(found);

        // Making sure that User is populated
       // assertTrue(found.getName().equals("Carson Mclean"));
    }


}
