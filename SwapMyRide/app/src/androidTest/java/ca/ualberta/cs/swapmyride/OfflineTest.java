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
import android.test.ApplicationTestCase;

/**
 * Created by carsonmclean on 9/10/15.
 */

public class OfflineTest extends ActivityInstrumentationTestCase2 {
    public OfflineTest() {super(MainMenu.class); }

    // Use Case 28: Add Vehicle Offline
    public void testAddItemOffline() {
        //TODO: find how to turn off data
        //android.turnOffData();

        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        //list should be empty
        assertTrue(inventoryList.getList().size() == 0);
        inventoryList.add(vehicle);

        //TODO: find how to turn on data
        //android.turnOnData();

        //make sure an vehicle was added
        assertTrue(inventoryList.size() == 1);
        //make sure it is the correct vehicle
        assertTrue(inventoryList.getList().get(0) == vehicle);
    }

    // Use Case 29: Make Trades Offline
    public void testMakeTradesOffline() {
        //TODO: find how to turn off data
        //android.turnOffData();

        //create users and their inventories
        User userOne = new User();
        User userTwo = new User();
        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        //create items and add to inventories
        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        userOneInventory.add(vehicleOne);
        //// TODO: Add picture back
        //vehicleOne.setPhoto(picture);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.COUPE);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
        //TODO: Add photo back into test
        //vehicleTwo.setPhoto(picture);
        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SUV);
        vehicleTwo.setQuality(VehicleQuality.OKAY);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);

        //add new trade, assert that it was created properly
        TradeList tradeList = new TradeList();
        Trade trade = new Trade(userOne, userTwo);
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);

        //TODO: find how to turn off data
        //android.turnOnData();

        assertTrue(tradeList.getTrades().size() == 0);
        tradeList.add(trade);
        assertTrue(tradeList.getSize() == 1);
        assertTrue(tradeList.get(0) == trade);
        assertTrue(trade.getOwnerItems().get(0).equals(vehicleOne));
        assertTrue(trade.getBorrowerItems().get(0).equals(vehicleTwo));
    }

    // Use Case 30: Browse Friends Offline
    public void testBrowseFriendsOffline() {
        FriendsList friendsList = new FriendsList();

        User userone = new User();
        User usertwo = new User();

        userone.setUserName("camclean");
        usertwo.setUserName("ccdunn");

        friendsList.addFriend(userone.getUserName());
        friendsList.addFriend(usertwo.getUserName());

        ////TODO: find how to turn off data
        //android.turnOffData();

        // Storing the returned User class in variable found
        Boolean found = friendsList.findUser(userone.getUserName());
        // Check if found is equal to what findUser gets
        assertTrue(found);

        //TODO: find how to turn on data
        //android.turnOnData();
    }
}
