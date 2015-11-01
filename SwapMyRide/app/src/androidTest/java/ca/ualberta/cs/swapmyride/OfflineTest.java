package ca.ualberta.cs.swapmyride;

import android.test.ApplicationTestCase;

/**
 * Created by carsonmclean on 9/10/15.
 */

public class OfflineTest extends ApplicationTestCase {
    public OfflineTest() {super(MainMenu.class); }

    // Use Case 28: Add Vehicle Offline
    public void testAddItemOffline() {
        android.turnOffData();

        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        //list should be empty
        assertTrue(inventoryList.getList() == null);
        inventoryList.add(vehicle);

        android.turnOnData();

        //make sure an vehicle was added
        assertTrue(inventoryList.size() == 1);
        //make sure it is the correct vehicle
        assertTrue(inventoryList.getList().get(0) == vehicle);
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
        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhoto(picture);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory("Sedan");
        vehicleOne.setQuality("Good");
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setVisibility("Public");
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhoto(picture);
        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory("SUV");
        vehicleTwo.setQuality("Good");
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setVisibility("Public");

        //add new trade, assert that it was created properly
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        android.turnOnData();

        assertTrue(tradeList.getList() = null);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() = 1);
        assertTrue(tradeList.get(0) = trade);
        assertTrue(trade.getOwnerItem() = vehicleOne);
        assertTrue(trade.getBorrowerItem() = vehicleTwo);
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
