package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by adrianomarini on 2015-10-08.
 */
public class TradeTest extends ActivityInstrumentationTestCase2 {
    public TradeTest(){super(MainMenu.class);}

    public void testCreateTrade(){
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);

        assertTrue(tradeList.getList() = null);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() = 1);
        assertTrue(tradeList.get(0) = trade);
    }

    public void testNotifyTrade(){
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item itemThree = new Item();
        Item itemFour = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.changeOwnerItem(itemOne, itemThree);
        trade.changeOwnerItem(itemTwo, itemFour);
        tradeList.add(trade);
        trade.send();

        assertTrue(trade.ownerNotified);
        assertTrue(trade.borrowerNotified);
    }

    public void testAcceptTrade(){
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.send()
        tradeList.add(trade);

        assertFalse(trade.isAccepted);
        trade.accept();
        assertTrue(trade.isAccepted);
    }

    public void testDeclineTrade(){
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.send();
        tradeList.add(trade);

        assertFalse(trade.isDeclined);
        trade.decline();
        assertTrue(trade.isDeclined);
    }

    public void testCounterTrade(){
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.send();
        tradeList.add(trade);

        assertFalse(trade.isDeclined);
        trade.decline();
        assertTrue(trade.isDeclined);

        Trade counterTrade = trade.makeCounterTrade();

        assertTrue(tradeList.size() = 2);
        assertTrue(counterTrade.getOwnerItem = itemOne);
        assertTrue(counterTrade.getBorrowerItem = itemTwo);
    }

    public void testEditTrade(){

        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item itemThree = new Item();
        Item itemFour = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        trade.changeOwnerItem(itemOne, itemThree);
        trade.changeOwnerItem(itemTwo, itemFour);

        tradeList.add(trade);

        assertTrue(trade.getOwnerItem = itemThree);
        assertTrue(trade.getBorrowerItem = itemFour);

    }

    public void testDeleteTrade(){

        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Item itemOne = new Item();
        Item itemTwo = new Item();
        Item itemThree = new Item();
        Item itemFour = new Item();

        userOneInventory.add(itemOne);
        userTwoInventory.add(itemTwo);

        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(itemOne);
        trade.addBorrowerItem(itemTwo);
        tradeList.add(trade);
        trade.delete();

        assertTrue(tradeList.size() = 0);
    }

    public void testSendEmail(){
        //no possible things to do there at the moment
    }

    public void testBrowseTrades() {
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
        userTwoInventory.add(itemTwo);
        userThreeInventory.add(itemThree);

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

        TradeList userOneTrades = TradeList.getUserTrades(userOne);
        TradeList userTwoTrades = TradeList.getUserTrades(userTwo);

        assertTrue(userOneTrades.size() = 4);
        assertTrue(userTwoTrades.size() = 4);

        assertTrue(userOneTrades.get(0) = trade);
        assertTrue(userTwoTrades.get(0) = trade2);
    }
}
