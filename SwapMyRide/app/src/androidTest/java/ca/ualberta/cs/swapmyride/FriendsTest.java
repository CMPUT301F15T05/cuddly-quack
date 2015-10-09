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

        User userone = new User();
        User usertwo = new User();

        userone.addUser("camclean");
        usertwo.addUser("ccdunn");

        friendsList.addFriend(userone);
        friendsList.addFriend(usertwo);

        // Storing the returned string in variable found
        User found = friendsList.findUser(userone);
        // Check if found is equal to what findUser gets
        assertTrue(found.equalTo(userone));
    }

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

    public void testViewProfiles() {
        FriendsList friendsList = new FriendsList();

        User user = new User();

        user.addUser("camclean");

        user.addUserName("Carson Mclean");

        friendsList.addFriend(user);

        User found = friendsList.findUser(user);

        assertTrue(found.hasUserName("Carson Mclean"));
    }


}
