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

        Trade counterTrade = new Trade(userOne, userTwo);
        

    }

    public void testEditTrade(){

    }

    public void testDeleteTrade(){

    }

    public void testSendEmail(){

    }

    public void testBrowseTrades(){

    }

}
