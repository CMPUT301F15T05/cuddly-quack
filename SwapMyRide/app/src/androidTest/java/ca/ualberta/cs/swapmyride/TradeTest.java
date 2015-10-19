package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adrianomarini on 2015-10-08.
 */
public class TradeTest extends ActivityInstrumentationTestCase2 {
    public TradeTest(){super(MainMenu.class);}

    //testing if create trade works
    public void testCreateTrade(){
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

        assertTrue(tradeList.getList() = null);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() = 1);
        assertTrue(tradeList.get(0) = trade);
        assertTrue(trade.getOwnerItem() = itemOne);
        assertTrue(trade.getBorrowerItem() = itemTwo)m;
    }

    //Test if trade notifications work
    public void testNotifyTrade(){
        //create users and inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create and send the trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);

        tradeList.add(trade);
        trade.send(); //trade.send() should have the functionality to notify

        //check that notification was done
        assertTrue(trade.ownerNotified);
        assertTrue(trade.borrowerNotified);
    }

    //test if accepting a trade works
    public void testAcceptTrade(){
        //create users, inventories, and items
        // add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.send();
        tradeList.add(trade);

        //check if accept works
        assertFalse(trade.isAccepted);
        trade.accept();
        assertTrue(trade.isAccepted);
    }

    public void testDeclineTrade(){
        //create users, inventories, and items
        //add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.send();
        tradeList.add(trade);

        //check if decline works
        assertFalse(trade.isDeclined);
        trade.decline();
        assertTrue(trade.isDeclined);
    }

    //test if a counter trade works
    public void testCounterTrade(){
        //create users, items, inventories, and add items to inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
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
        assertTrue(counterTrade.getOwnerItem = itemOne);
        assertTrue(counterTrade.getBorrowerItem = itemTwo);
    }

    //testing edit functionality
    public void testEditTrade(){
        //create users, items, and the inventories
        // add items to inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item itemThree = new Item();
        Item itemFour = new Item();

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

        userOneInventory.add(itemThree);
        itemThree.setPhoto(picture);
        itemThree.setName("Chrysler");
        itemThree.setCategory("Sedan");
        itemThree.setQuality("Good");
        itemThree.setQuantity(1);
        itemThree.setComments("2001 Cadillac");
        itemThree.setVisibility("Public");
        userTwoInventory.add(itemFour);
        itemFour.setPhoto(picture);
        itemFour.setName("Jeep");
        itemFour.setCategory("SUV");
        itemFour.setQuality("Good");
        itemFour.setQuantity(1);
        itemFour.setComments("2014 Jeep");
        itemFour.setVisibility("Public");

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);

        //swap items
        trade.changeOwnerItem(itemOne, itemThree);
        trade.changeOwnerItem(itemTwo, itemFour);

        tradeList.add(trade);
        //check if the swap worked.
        assertTrue(trade.getOwnerItem = itemThree);
        assertTrue(trade.getBorrowerItem = itemFour);

    }

    public void testDeleteTrade(){
        //create users, items, and inventories
        //add items to inventory
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

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

        //create trade and then delete it
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        tradeList.add(trade);
        trade.delete();
        //make sure delete was effective
        assertTrue(tradeList.size() = 0);
    }

    public void testSendEmail(){
        //no possible things to do there at the moment
    }

    public void testBrowseTrades() {
        //create users, items, and inventories, add items to inventory
        User userOne = new User();
        User userTwo = new User();
        User userThree = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();
        InventoryList userThreeInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item itemThree = new Item();

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
        userOneInventory.add(itemThree);
        itemThree.setPhoto(picture);
        itemThree.setName("Chrysler");
        itemThree.setCategory("Sedan");
        itemThree.setQuality("Good");
        itemThree.setQuantity(1);
        itemThree.setComments("2001 Cadillac");
        itemThree.setVisibility("Public");

        //create some trades
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        tradeList.add(trade);

        Trade trade2 = new Trade(userOne, userThree);
        trade2.addOwnerItem(itemOne);
        trade2.addBorrowerItem(itemThree);
        tradeList.add(trade2);

        Trade trade3 = new Trade(userOne, userTwo);
        trade3.addOwnerItem(itemOne);
        trade3.addBorrowerItem(itemTwo);
        tradeList.add(trade3);

        Trade trade4 = new Trade(userOne, userTwo);
        trade4.addOwnerItem(itemOne);
        trade4.addBorrowerItem(itemTwo);
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
