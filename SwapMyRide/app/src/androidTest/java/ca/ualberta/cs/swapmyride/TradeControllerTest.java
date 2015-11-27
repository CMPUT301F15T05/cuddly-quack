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

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Controller.TradesController;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.TradeList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Daniel on 2015-11-26.
 */

public class TradeControllerTest extends ActivityInstrumentationTestCase2 {
    public TradeControllerTest(){
        super(MainMenu.class);
    }

    public void testValidTrade() {
        User userOne = new User();
        User userTwo = new User();

        TradesController tradesController = new TradesController(getActivity());
        DataManager dataManager = new DataManager(getActivity());

        userOne.setUserName("dhaberst");
        userTwo.setUserName("ccdunn");

        InventoryList userOneInventory = new InventoryList();
        InventoryList userTwoInventory = new InventoryList();

        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();

        userOneInventory.add(vehicleOne);
        vehicleOne.setName("Cadillac");
        vehicleOne.setCategory(VehicleCategory.SEDAN);
        vehicleOne.setQuality(VehicleQuality.GOOD);
        vehicleOne.setQuantity(1);
        vehicleOne.setComments("1995 Cadillac");
        vehicleOne.setPublic(true);
        userTwoInventory.add(vehicleTwo);
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



    }

    public void testDeleteTrade() {

    }

}
