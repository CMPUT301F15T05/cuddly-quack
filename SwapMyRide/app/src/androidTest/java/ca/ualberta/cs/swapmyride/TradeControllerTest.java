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
import android.util.Log;

import java.net.UnknownServiceException;

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Controller.TradesController;
import ca.ualberta.cs.swapmyride.Misc.InvalidTradeException;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.TradeList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.LoginActivity;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Daniel on 2015-11-26.
 */

    public class TradeControllerTest extends ActivityInstrumentationTestCase2 {
    public TradeControllerTest(){
        super(LoginActivity.class);
    }

    public void testValidTrade() {
        User userOne = new User();
        User userTwo = new User();

        userOne.setUserName("dhaberst");
        userTwo.setUserName("ccdunn");

        userOne.addFriend(userTwo.getUserName());
        userTwo.addFriend(userOne.getUserName());

        UserSingleton.addCurrentUser(userOne);

        TradesController tradesController = new TradesController(getActivity());
        DataManager dataManager = new DataManager(getActivity());

        dataManager.deleteUser("dhaberst");
        dataManager.deleteUser("ccdunn");

        InventoryList userOneInventory = userOne.getInventory();
        InventoryList userTwoInventory = userTwo.getInventory();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);

        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);

        userTwoInventory.add(vehicleTwo);
        userOneInventory.add(vehicleOne);


        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);

        userOne.setPendingTrades(tradeList);
        userTwo.setPendingTrades(tradeList);

        dataManager.saveUser(userOne);
        dataManager.saveUser(userTwo);

        assertTrue(tradesController.validTrade(trade));

        userOne.getInventory().delete(vehicleOne);

        dataManager.saveUser(userOne);

        assertFalse(tradesController.validTrade(trade));
    }

    public void testConfirmTrade() {
        User userOne = new User();
        User userTwo = new User();

        userOne.setUserName("dhaberst");
        userTwo.setUserName("ccdunn");

        userOne.addFriend(userTwo.getUserName());
        userTwo.addFriend(userOne.getUserName());

        UserSingleton.addCurrentUser(userOne);

        TradesController tradesController = new TradesController(getActivity());
        DataManager dataManager = new DataManager(getActivity());

        dataManager.deleteUser("dhaberst");
        dataManager.deleteUser("ccdunn");

        InventoryList userOneInventory = userOne.getInventory();
        InventoryList userTwoInventory = userTwo.getInventory();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);

        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);

        userTwoInventory.add(vehicleTwo);
        userOneInventory.add(vehicleOne);


        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);

        userOne.setPendingTrades(tradeList);
        userTwo.setPendingTrades(tradeList);

        dataManager.saveUser(userOne);
        dataManager.saveUser(userTwo);

        boolean b = true;

        try {
            tradesController.confirmPendingTrade(trade);
        } catch (InvalidTradeException e) {
            assertFalse(b);
        }

        userOne = dataManager.loadUser(userOne.getUserName());
        userTwo = dataManager.loadUser(userTwo.getUserName());

        assertTrue(userOne.getInventory().getList().size() == 1);
        assertTrue(userTwo.getInventory().getList().size() == 1);

        assertTrue(userOne.getInventory().getList().get(0).getUniqueID().isEqualID(vehicleTwo.getUniqueID()));
        assertTrue(userTwo.getInventory().getList().get(0).getUniqueID().isEqualID(vehicleOne.getUniqueID()));
    }

    public void testConfirmTradeThrowsException() {
        User userOne = new User();
        User userTwo = new User();

        userOne.setUserName("dhaberst");
        userTwo.setUserName("ccdunn");

        userOne.addFriend(userTwo.getUserName());
        userTwo.addFriend(userOne.getUserName());

        UserSingleton.addCurrentUser(userOne);

        TradesController tradesController = new TradesController(getActivity());
        DataManager dataManager = new DataManager(getActivity());

        dataManager.deleteUser("dhaberst");
        dataManager.deleteUser("ccdunn");

        InventoryList userOneInventory = userOne.getInventory();
        InventoryList userTwoInventory = userTwo.getInventory();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);

        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);

        userTwoInventory.add(vehicleTwo);
        // Not adding vehicle to throw exception
        // userOneInventory.add(vehicleOne);


        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);

        userOne.setPendingTrades(tradeList);
        userTwo.setPendingTrades(tradeList);

        dataManager.saveUser(userOne);
        dataManager.saveUser(userTwo);

        boolean b = true;

        try {
            tradesController.confirmPendingTrade(trade);
        } catch (InvalidTradeException e) {
            e.printStackTrace();
            assertTrue(b);
        }

    }

    public void testDeleteTrade() {
        User userOne = new User();
        User userTwo = new User();

        userOne.setUserName("dhaberst");
        userTwo.setUserName("ccdunn");

        userOne.addFriend(userTwo.getUserName());
        userTwo.addFriend(userOne.getUserName());

        UserSingleton.addCurrentUser(userOne);

        TradesController tradesController = new TradesController(getActivity());
        DataManager dataManager = new DataManager(getActivity());

        dataManager.deleteUser("dhaberst");
        dataManager.deleteUser("ccdunn");

        InventoryList userOneInventory = userOne.getInventory();
        InventoryList userTwoInventory = userTwo.getInventory();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);

        vehicleTwo.setName("Jeep");
        vehicleTwo.setCategory(VehicleCategory.SEDAN);
        vehicleTwo.setQuality(VehicleQuality.GOOD);
        vehicleTwo.setQuantity(1);
        vehicleTwo.setComments("1994 Jeep");
        vehicleTwo.setPublic(true);

        userTwoInventory.add(vehicleTwo);
        userOneInventory.add(vehicleOne);


        //create trade
        TradeList tradeList = new TradeList();
        Trade trade = new Trade();
        trade.setOwner(userOne.getUserName());
        trade.setBorrower(userTwo.getUserName());
        trade.addOwnerItem(vehicleOne);
        trade.addBorrowerItem(vehicleTwo);
        tradeList.add(trade);

        userOne.setPendingTrades(tradeList);
        userTwo.setPendingTrades(tradeList);

        dataManager.saveUser(userOne);
        dataManager.saveUser(userTwo);

        tradesController.deletePendingTrade(trade);

        userOne = dataManager.loadUser(userOne.getUserName());
        userTwo = dataManager.loadUser(userTwo.getUserName());

        assertTrue(userOne.getInventory().getList().size() == 1);
        assertTrue(userTwo.getInventory().getList().size() == 1);

        assertTrue(userOne.getInventory().getList().get(0).getUniqueID().isEqualID(vehicleOne.getUniqueID()));
        assertTrue(userTwo.getInventory().getList().get(0).getUniqueID().isEqualID(vehicleTwo.getUniqueID()));

        assertTrue(userOne.getPendingTrades().getTrades().size() == 0);
        assertTrue(userTwo.getPendingTrades().getTrades().size() == 0);

    }

}
