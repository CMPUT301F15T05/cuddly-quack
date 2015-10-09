package ca.ualberta.cs.swapmyride;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by carsonmclean on 9/10/15.
 */

public class OfflineTest extends ApplicationTestCase {
    public OfflineTest() {super(MainMenu.class); }

    // Use Case 28: Add Item Offline
    public void testAddItemOffline() {
        android.turnOffData();

        InventoryList inventoryList = new InventoryList();
        Item item = new Item();
        //list should be empty
        assertTrue(inventoryList.getList() = null);
        inventoryList.add(item);

        android.turnOnData();

        //make sure an item was added
        assetTrue(inventoryList.size() == 1);
        //make sure it is the correct item
        assertTrue(inventoryList.getList().get(0) == item);
    }

    // Use Case 29: Make Trades Offline
    public void testMakeTradesOffline() {
        android.turnOffData();

        //create users and their inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        //create items and add to inventories
        Item itemOne = new Item();
        Item itemTwo = new Item();

        userOneInventory.add(itemOne);
        itemOne.setPhoto(picture);
        itemOne.setName("Cadillac");
        itemOne.setCategory("Sedan");
        itemOne.setQuality("Good");
        itemOne.setQuantity(1);
        itemOne.setComments("1995 Cadillac");
        itemOne.setVisibility("Public");
        userTwoInventory.add(itemTwo);
        itemTwo.setPhoto(picture);
        itemTwo.setName("Jeep");
        itemTwo.setCategory("SUV");
        itemTwo.setQuality("Good");
        itemTwo.setQuantity(1);
        itemTwo.setComments("1994 Jeep");
        itemTwo.setVisibility("Public");

        //add new trade, assert that it was created properly
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);

        android.turnOnData();

        assertTrue(tradeList.getList() = null);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() = 1);
        assertTrue(tradeList.get(0) = trade);
        assertTrue(trade.getOwnerItem() = itemOne);
        assertTrue(trade.getBorrowerItem() = itemTwo)m;
    }

    // Use Case 30: Browse Friends Offline
    public void testBrowseFriendsOffline() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.addUser("camclean");
        usertwo.addUser("ccdunn");

        friendsList.addFriend(userone);
        friendsList.addFriend(usertwo);

        android.turnOffData();

        // Storing the returned User class in variable found
        User found = friendsList.findUser(userone);
        // Check if found is equal to what findUser gets
        assertTrue(found.equalTo(userone));

        android.turnOnData();
    }
}
