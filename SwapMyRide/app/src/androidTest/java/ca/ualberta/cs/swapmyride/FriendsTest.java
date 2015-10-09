package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

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

        userone.addUser("camclean");
        usertwo.addUser("ccdunn");

        friendsList.addFriend(userone);
        friendsList.addFriend(usertwo);

        // Storing the returned User class in variable found
        User found = friendsList.findUser(userone);
        // Check if found is equal to what findUser gets
        assertTrue(found.equalTo(userone));
    }

    // Use Case 9: Add Friend
    public void testAddFriend() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.addUser("camclean");
        usertwo.addUser("ccdunn");

        friendsList.addFriend(userone);
        friendsList.addFriend(usertwo);

        // Making sure that friendslist has camclean
        assertTrue(friendsList.hasUser(userone));
    }

    // Use Case 10: Remove Friend
    public void testRemoveFriend() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.addUser("camclean");
        usertwo.addUser("ccdunn");

        friendsList.addFriend(userone);
        friendsList.addFriend(usertwo);

        friendsList.removeFriend(userone);

        // Making sure that friendslist has camclean
        assertFalse(friendsList.hasUser(userone));
    }

    // Use Case 11: User Profile
    public void testUserProfile() {
        User user = new User();
        user.addUser("camclean");
        user.addUserName("Carson Mclean");
        user.addUserEmail("camclean@ualberta.ca");
        user.addUserAddress("300 Shirley Street");

        // Making sure that user has these attributes
        asserTrue(user.hasUserName("Carson Mclean"));
        asserTrue(user.hasUserEmail("camclean@ualberta.ca"));
        asserTrue(user.hasUserAddress("300 Shirley Street"));
    }

    // Use Case 12: View Profiles
    public void testViewProfiles() {
        FriendsList friendsList = new FriendsList();

        User user = new User();

        user.addUser("camclean");
        user.addUserName("Carson Mclean");

        friendsList.addFriend(user);

        // Storing the returned User class in variable found
        User found = friendsList.findUser(user);

        // Making sure that User is populated
        assertTrue(found.hasUserName("Carson Mclean"));
    }


}
