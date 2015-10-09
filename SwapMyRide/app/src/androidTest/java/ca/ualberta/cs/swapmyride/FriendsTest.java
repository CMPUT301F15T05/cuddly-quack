package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Daniel on 2015-10-08.
 */
public class FriendsTest  extends ActivityInstrumentationTestCase2 {
    public FriendsTest(){
        super(MainMenu.class);
    }

    public void testFindUser() {
        FriendsList friendsList = new FriendsList();
        friendsList.addFriend("camclean");
        friendsList.addFriend("ccdunn");
        // Storing the returned string in variable found
        String found = friendsList.findUser("camclean");
        // Check if found is equal to what findUser gets
        assertEquals(found,"camclean");
    }

    public void testAddFriend() {
        FriendsList friendsList = new FriendsList();
        friendsList.addFriend("camclean");
        friendsList.addFriend("ccdunn");

    }

    public void testRemoveFriend() {

    }

    public void testUserProfile() {

    }

    public void testViewProfiles() {

    }


}
