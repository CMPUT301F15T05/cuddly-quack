package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adrianomarini on 2015-10-08.
 */
public class TradeTest extends ActivityInstrumentationTestCase2 {
    public TradeTest(){super(MainMenu.class);}

    // Use Case 15: Trade with Friend
    //testing if create trade works
    public void testCreateTrade(){
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

        assertTrue(tradeList.getList() = null);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() = 1);
        assertTrue(tradeList.get(0) = trade);
        assertTrue(trade.getOwnerItem() = vehicleOne);
        assertTrue(trade.getBorrowerItem() = vehicleTwo);
    }

    // Use Case 16: Notify
    //Test if trade notifications work
    public void testNotifyTrade(){
        //create users and inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create and send the trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        tradeList.add(trade);
        trade.send(); //trade.send() should have the functionality to notify

        //check that notification was done
        assertTrue(trade.ownerNotified);
        assertTrue(trade.borrowerNotified);
    }

    // Use Case 17: Respond to Trade
    //test if accepting a trade works
    public void testAcceptTrade(){
        //create users, inventories, and items
        // add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //check if accept works
        assertFalse(trade.isAccepted);
        trade.accept();
        assertTrue(trade.isAccepted);
    }

    // Use Case 17: Respond to Trade
    public void testDeclineTrade(){
        //create users, inventories, and items
        //add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //check if decline works
        assertFalse(trade.isDeclined);
        trade.decline();
        assertTrue(trade.isDeclined);
    }

    // Use Case 18: Counter a Trade
    //test if a counter trade works
    public void testCounterTrade(){
        //create users, items, inventories, and add items to inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //decline the trade
        assertFalse(trade.isDeclined);
        trade.decline();
        assertTrue(trade.isDeclined);

        //test counter trade by creating one and ensuring that
        //  items are initialized properly
        Trade counterTrade = trade.makeCounterTrade();

        assertTrue(tradeList.size() = 2);
        assertTrue(counterTrade.getOwnerItem = vehicleOne);
        assertTrue(counterTrade.getBorrowerItem = vehicleTwo);
    }

    // Use Case 19: Edit Trade
    //testing edit functionality
    public void testEditTrade(){
        //create users, items, and the inventories
        // add items to inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();
        Vehicle vehicleThree = new Vehicle();
        Vehicle vehicleFour = new Vehicle();

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

        userOneInventory.add(vehicleThree);
        vehicleThree.setPhoto(picture);
        vehicleThree.setName("Chrysler");
        vehicleThree.setCategory("Sedan");
        vehicleThree.setQuality("Good");
        vehicleThree.setQuantity(1);
        vehicleThree.setComments("2001 Cadillac");
        vehicleThree.setVisibility("Public");
        userTwoInventory.add(vehicleFour);
        vehicleFour.setPhoto(picture);
        vehicleFour.setName("Jeep");
        vehicleFour.setCategory("SUV");
        vehicleFour.setQuality("Good");
        vehicleFour.setQuantity(1);
        vehicleFour.setComments("2014 Jeep");
        vehicleFour.setVisibility("Public");

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        //swap items
        trade.changeOwnerItem(vehicleOne, vehicleThree);
        trade.changeOwnerItem(vehicleTwo, vehicleFour);

        tradeList.add(trade);
        //check if the swap worked.
        assertTrue(trade.getOwnerItem = vehicleThree);
        assertTrue(trade.getBorrowerItem = vehicleFour);

    }

    // Use Case 20: Delete Trade
    public void testDeleteTrade(){
        //create users, items, and inventories
        //add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade and then delete it
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);
        trade.delete();
        //make sure delete was effective
        assertTrue(tradeList.size() = 0);
    }

    // Use Case 21: Email Parties
    public void testSendEmail(){
        //no possible things to do there at the moment
    }

    // Use Case 22: See Trades
    public void testBrowseTrades() {
        //create users, items, and inventories, add items to inventory
        User userOne = new User();
        User userTwo = new User();
        User userThree = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();
        InventoryList userThreeInventory = new InventoryList();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();
        Vehicle vehicleThree = new Vehicle();

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
        userOneInventory.add(vehicleThree);
        vehicleThree.setPhoto(picture);
        vehicleThree.setName("Chrysler");
        vehicleThree.setCategory("Sedan");
        vehicleThree.setQuality("Good");
        vehicleThree.setQuantity(1);
        vehicleThree.setComments("2001 Cadillac");
        vehicleThree.setVisibility("Public");

        //create some trades
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);

        Trade trade2 = new Trade(userOne, userThree);
        trade2.addOwnerItem(vehicleOne);
        trade2.addBorrowerItem(vehicleThree);
        tradeList.add(trade2);

        Trade trade3 = new Trade(userOne, userTwo);
        trade3.addOwnerItem(vehicleOne);
        trade3.addBorrowerItem(vehicleTwo);
        tradeList.add(trade3);

        Trade trade4 = new Trade(userOne, userTwo);
        trade4.addOwnerItem(vehicleOne);
        trade4.addBorrowerItem(vehicleTwo);
        tradeList.add(trade4);

        //test to make sure that the function ensures that only trades
        //  involving a certain person are collected and displayed.
        TradeList userOneTrades = TradeList.getUserTrades(userOne);
        TradeList userTwoTrades = TradeList.getUserTrades(userTwo);

        assertTrue(userOneTrades.size() = 4);
        assertTrue(userTwoTrades.size() = 4);

        assertTrue(userOneTrades.get(0) = trade);
        assertTrue(userTwoTrades.get(0) = trade2);
    }
}
