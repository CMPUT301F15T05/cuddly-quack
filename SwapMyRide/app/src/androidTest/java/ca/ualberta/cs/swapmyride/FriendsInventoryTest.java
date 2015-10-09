package ca.ualberta.cs.swapmyride;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by carsonmclean on 9/10/15.
 */
public class FriendsInventoryTest extends ApplicationTestCase {
    public FriendsInventoryTest() {super(MainMenu.class); }

    public void testSearchFriendsInventory() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.addUser("camclean");
        user.addUserName("Carson Mclean");
        user.addUserEmail("camclean@ualberta.ca");
        user.addUserAddress("300 Shirley Street");

        Item item = new Item();

        item.setPhoto(picture);
        item.setName("Cadillac");
        item.setCategory("Sedan");
        item.setQuality("Good");
        item.setQuantity(1);
        item.setComments("1995 Cadillac");

        user.addItem(item);

        assertTrue(user.getInventory().search("Cadillac"));
        assertTrue(user.getInventory().getCategory("Sedan").size() == 1);
    }

    public void testMakeItemsVisible() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.addUser("camclean");
        user.addUserName("Carson Mclean");
        user.addUserEmail("camclean@ualberta.ca");
        user.addUserAddress("300 Shirley Street");

        Item item = new Item();

        item.setPhoto(picture);
        item.setName("Cadillac");
        item.setCategory("Sedan");
        item.setQuality("Good");
        item.setQuantity(1);
        item.setComments("1995 Cadillac");
        item.setVisibility("Private");

        assertTrue(user.getInventory().search("Cadillac".size == 0));

        item.setVisibility("Public");

        assertTrue(user.getInventory().search("Cadillac".size == 1));
    }
}
