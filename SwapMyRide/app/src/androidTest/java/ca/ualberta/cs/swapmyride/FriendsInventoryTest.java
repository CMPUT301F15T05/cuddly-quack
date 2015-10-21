package ca.ualberta.cs.swapmyride;

import android.test.ApplicationTestCase;

/**
 * Created by carsonmclean on 9/10/15.
 */
public class FriendsInventoryTest extends ApplicationTestCase {
    public FriendsInventoryTest() {super(MainMenu.class); }

    // Use Case 13: Search Friends Inventory
    public void testSearchFriendsInventory() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.addUser("camclean");
        user.addUserName("Carson Mclean");
        user.addUserEmail("camclean@ualberta.ca");
        user.addUserAddress("300 Shirley Street");

        Vehicle vehicle = new Vehicle();

        vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory("Sedan");
        vehicle.setQuality("Good");
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");

        user.addItem(vehicle);

        assertTrue(user.getInventory().search("Cadillac"));
        assertTrue(user.getInventory().getCategory("Sedan").size() == 1);
    }

    // Use Case 14: Make Items Visible
    public void testMakeItemsVisible() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.addUser("camclean");
        user.addUserName("Carson Mclean");
        user.addUserEmail("camclean@ualberta.ca");
        user.addUserAddress("300 Shirley Street");

        Vehicle vehicle = new Vehicle();

        vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory("Sedan");
        vehicle.setQuality("Good");
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");
        vehicle.setVisibility("Private");

        assertTrue(user.getInventory().search("Cadillac".size == 0));

        vehicle.setVisibility("Public");

        assertTrue(user.getInventory().search("Cadillac".size == 1));
    }
}
