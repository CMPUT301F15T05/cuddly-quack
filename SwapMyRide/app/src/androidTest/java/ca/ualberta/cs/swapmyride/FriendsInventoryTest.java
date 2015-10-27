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

        user.setUser("camclean");
        user.setUserName("Carson Mclean");
        user.setUserEmail("camclean@ualberta.ca");
        user.setUserAddress("300 Shirley Street");

        Vehicle vehicle = new Vehicle();

        vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory(VehicleCategory.COUPE);
        vehicle.setQuality(VehicleQuality.GOOD);
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");

        user.addItem(vehicle);

        //check that a given vehicle exists by name
        assertTrue(user.getInventory().search("Cadillac"));

        //get a list of all vehicles of a category
        assertTrue(user.getInventory().getCategory(VehicleCategory.COUPE).size() == 1);
    }

    // Use Case 14: Make Items Visible
    public void testMakeItemsVisible() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.setUser("camclean");
        user.setUserName("Carson Mclean");
        user.setUserEmail("camclean@ualberta.ca");
        user.setUserAddress("300 Shirley Street");

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
