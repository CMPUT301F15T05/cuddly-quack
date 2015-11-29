/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.NotificationManager;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.TradeList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by adrianomarini on 2015-10-08.
 */
public class TradeTest extends ActivityInstrumentationTestCase2 {
    public TradeTest(){super(MainMenu.class);}
    public Photo picture;
    public ArrayList<Photo> photoArrayList;

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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userOneInventory.add(vehicleOne);

        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);
        userTwoInventory.add(vehicleTwo);

        //add new trade, assert that it was created properly
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        assertTrue(tradeList.getTrades().size() == 0);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() == 1);
        assertTrue(tradeList.get(0).equals(trade));

        ArrayList<Vehicle> testOne = trade.getOwnerItems();
        ArrayList<Vehicle> testTwo = trade.getBorrowerItems();

        assertTrue(testOne.get(0).equals(vehicleOne));
        assertTrue(testTwo.get(0).equals(vehicleTwo));
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        //create and send the trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        tradeList.add(trade);
        NotificationManager nm = new NotificationManager();
        nm.notifyTrade(trade);

        //check that notification was done
        assertTrue(trade.getOwnerNotified());  // TODO: double check that these vars are updated
        assertTrue(trade.getBorrowerNotified());  // TODO: double check that these vars are updated
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //check if accept works
        assertFalse(trade.getIsAccepted());
        trade.accept();
        assertTrue(trade.getIsAccepted());
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //check if decline works
        assertFalse(trade.getIsDeclined());
        trade.decline();
        assertTrue(trade.getIsDeclined());
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        trade.send();
        tradeList.add(trade);

        //decline the trade
        assertFalse(trade.getIsDeclined());
        trade.decline();
        assertTrue(trade.getIsDeclined());

        //test counter trade by creating one and ensuring that
        //  items are initialized properly
        Trade counterTrade = trade.makeCounterTrade();

        //assertTrue(tradeList.getSize() == 2);

        ArrayList<Vehicle> testOne = counterTrade.getOwnerItems();
        ArrayList<Vehicle> testTwo = counterTrade.getBorrowerItems();

        assertTrue(testOne.get(0).equals(vehicleTwo));
        assertTrue(testTwo.get(0).equals(vehicleOne));
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        userOneInventory.add(vehicleThree);
        vehicleThree.setPhotoArrayList(photoArrayList);
        vehicleThree.setName("Chrysler");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleThree.setQuantity(1);
        vehicleThree.setComments("2001 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleFour);
        vehicleFour.setPhotoArrayList(photoArrayList);
        vehicleFour.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleFour.setQuantity(1);
        vehicleFour.setComments("2014 Jeep");
        vehicleOne.setPublic(true);

        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        //swap items
        trade.changeOwnerVehicle(vehicleOne, vehicleThree);
        trade.changeBorrowerVehicle(vehicleTwo, vehicleFour);

        tradeList.add(trade);
        //check if the swap worked.

        ArrayList<Vehicle> testOne = trade.getOwnerItems();
        ArrayList<Vehicle> testTwo = trade.getBorrowerItems();

        assertTrue(testOne.get(0).equals(vehicleThree));
        //assertTrue(testTwo.get(0).equals(vehicleFour));

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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);

        //create trade and then delete it
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);
        tradeList.delete(trade);
        //make sure delete was effective
        assertTrue(tradeList.getSize() == 0);
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

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        userOneInventory.add(vehicleOne);
        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleOne.setPublic(true);
        userOneInventory.add(vehicleThree);
        vehicleThree.setPhotoArrayList(photoArrayList);
        vehicleThree.setName("Chrysler");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleThree.setQuantity(1);
        vehicleThree.setComments("2001 Cadillac");
        vehicleOne.setPublic(true);

        //create some trades
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);
        userOne.addPendingTrade(trade);
        userTwo.addPendingTrade(trade);

        Trade trade2 = new Trade();
        trade2.setOwner(userOne.getUserName());
        trade2.setBorrower(userTwo.getUserName());
        trade2.addOwnerItem(vehicleOne);
        trade2.addBorrowerItem(vehicleThree);
        tradeList.add(trade2);
        userOne.addPendingTrade(trade2);
        userThree.addPendingTrade(trade2);

        Trade trade3 = new Trade();
        trade3.setOwner(userOne.getUserName());
        trade3.setBorrower(userTwo.getUserName());
        trade3.addOwnerItem(vehicleOne);
        trade3.addBorrowerItem(vehicleTwo);
        tradeList.add(trade3);
        userOne.addPastTrade(trade3);
        userTwo.addPastTrade(trade3);

        Trade trade4 = new Trade();
        trade4.setOwner(userOne.getUserName());
        trade4.setBorrower(userTwo.getUserName());
        trade4.addOwnerItem(vehicleOne);
        trade4.addBorrowerItem(vehicleTwo);
        tradeList.add(trade4);
        userOne.addPastTrade(trade4);
        userThree.addPastTrade(trade4);

        //test to make sure that the function ensures that only trades
        //  involving a certain person are collected and displayed.
        ArrayList<Trade> userOneTrades = tradeList.getUserTrades(userOne);
        ArrayList<Trade> userTwoTrades = tradeList.getUserTrades(userTwo);

        assertTrue(userOneTrades.size() == 4);
        assertTrue(userTwoTrades.size() == 2);

        assertTrue(userOneTrades.get(0).equals(trade));
        assertTrue(userTwoTrades.get(0).equals(trade));
    }

    public void testCopy(){
        //create users and their inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();
        userOne.setName("John Smith");
        userTwo.setName("Jane Smith");
        userOne.setUserName("john");
        userTwo.setUserName("jane");

        //create items and add to inventories
        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        picture = new Photo(getActivity());
        photoArrayList = new ArrayList<>();
        photoArrayList.add(picture);

        vehicleOne.setPhotoArrayList(photoArrayList);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userOneInventory.add(vehicleOne);

        vehicleTwo.setPhotoArrayList(photoArrayList);
        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);
        userTwoInventory.add(vehicleTwo);

        //add new trade, assert that it was created properly
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        Trade copyTrade = trade.copy();
        assertTrue(trade.getUniqueID().isEqualID(copyTrade.getUniqueID()));
        assertTrue(trade.getOwner().equals(copyTrade.getOwner()));
        // TODO: Add a lot more assertions if someone feels like it, the UniqueID is what we most care about
    }
}
