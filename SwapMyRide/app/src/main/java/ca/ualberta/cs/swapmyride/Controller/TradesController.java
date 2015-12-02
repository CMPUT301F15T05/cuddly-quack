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
package ca.ualberta.cs.swapmyride.Controller;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.InvalidTradeException;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.TradeList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.FeedTradeActivity;

/**
 *
 * Trade controller helps us deal with data and saving related to the creation,
 * and actions associated with Trades in the app.
 *
 */
public class TradesController {

    Context context;
    NetworkDataManager dataManager;
    LocalDataManager dataManagerLocal;

    public TradesController(Context context) {
        this.context = context;
        dataManager = new NetworkDataManager();
        dataManagerLocal = new LocalDataManager(context);
    }

    /**
     * Creates and saves a new trade after all of the options are selected
     * and confirmed in the UI
     */

    public void initiateTrade() {
        Trade pendingTrade = UserSingleton.getCurrentTrade().copy();

        User friend = dataManager.retrieveUser(pendingTrade.getBorrower());

        friend.addPendingTrade(pendingTrade);
        UserSingleton.getCurrentUser().addPendingTrade(pendingTrade);

        friend.getNotificationManager().notifyTrade(pendingTrade);
        UserSingleton.getCurrentUser().getNotificationManager().notifyTrade(pendingTrade);

        dataManager.saveUser(friend);
        dataManager.saveUser(UserSingleton.getCurrentUser());

        dataManagerLocal.saveUser(friend);
        dataManagerLocal.saveUser(UserSingleton.getCurrentUser());
    }

    /**
     * Creates and saves a new borrow type trade after all of the options are selected
     * and confirmed in the UI
     */

    public void initiateBorrow() {
        Trade pendingTrade = UserSingleton.getCurrentTrade().copy();

        pendingTrade.setBorrowing(true);

        User friend = dataManager.retrieveUser(pendingTrade.getBorrower());

        friend.addPendingTrade(pendingTrade);
        UserSingleton.getCurrentUser().addPendingTrade(pendingTrade);

        friend.getNotificationManager().notifyTrade(pendingTrade);
        UserSingleton.getCurrentUser().getNotificationManager().notifyTrade(pendingTrade);

        dataManager.saveUser(friend);
        dataManager.saveUser(UserSingleton.getCurrentUser());

        dataManagerLocal.saveUser(friend);
        dataManagerLocal.saveUser(UserSingleton.getCurrentUser());
    }

    /**
     *
     * Deletes a pending trade from potential lists and makes sure no trace
     * exists in the app.
     *
     * @param trade
     */

    public void deletePendingTrade(Trade trade) {
        User borrower = dataManager.retrieveUser(trade.getBorrower());
        User owner = dataManager.retrieveUser(trade.getOwner());

        TradeList borrowerPendingTrades = borrower.getPendingTrades();
        borrowerPendingTrades.delete(trade.getUniqueID());
        borrower.setPendingTrades(borrowerPendingTrades);

        TradeList userPendingTrades = owner.getPendingTrades();
        userPendingTrades.delete(trade.getUniqueID());
        owner.setPendingTrades(userPendingTrades);

        owner.getNotificationManager().removeTrade(trade);
        borrower.getNotificationManager().removeTrade(trade);

        // make sure to save the right user back to the userSingleton!
        if (borrower.getUserName().equals(UserSingleton.getCurrentUser().getUserName())) {
            UserSingleton.addCurrentUser(borrower);
        } else {
            UserSingleton.addCurrentUser(owner);
        }

        dataManager.saveUser(borrower);
        dataManager.saveUser(owner);

        dataManagerLocal.saveUser(borrower);
        dataManagerLocal.saveUser(owner);
    }

    /**
     * Facilitates the action of accepting a pending trade and swapping items
     * If the trade is of borrow type, it is marked as confirmed, but no items
     * switch hands
     *
     * @param trade
     * @throws InvalidTradeException
     */

    public void confirmPendingTrade(Trade trade) throws InvalidTradeException {
        // check that items are in inventory for both parties
        if (!(validTrade(trade))) {
            throw new InvalidTradeException("Trade is no longer valid");
        }

        if (trade.getIsBorrowing()) {
            confirmBorrowTrade(trade);
            return;
        }

        User borrower = dataManager.retrieveUser(trade.getBorrower());
        User owner = dataManager.retrieveUser(trade.getOwner());

        trade.swapBelongsTo();

        // swap items between users
        owner.getInventory().getList().addAll(trade.getBorrowerItems());
        borrower.getInventory().deleteAll(trade.getBorrowerItems());

        borrower.getInventory().getList().addAll(trade.getOwnerItems());
        owner.getInventory().deleteAll(trade.getOwnerItems());

        // remove from pendingList
        owner.getPendingTrades().delete(trade.getUniqueID());
        borrower.getPendingTrades().delete(trade.getUniqueID());

        // add to pastTradesList
        owner.addPastTrade(trade);
        borrower.addPastTrade(trade);

        // save users
        dataManager.saveUser(borrower);
        dataManager.saveUser(owner);

        dataManagerLocal.saveUser(borrower);
        dataManagerLocal.saveUser(owner);

        // save userSingleton
        UserSingleton.addCurrentUser(borrower);
    }

    /**
     *
     * If a trade is accepted but still pending, this handles the return of an
     * item to the original item, making it available for use again.
     *
     * @param trade
     * @throws InvalidTradeException
     */

    public void returnPendingTrade(Trade trade) throws InvalidTradeException {
        // check that items are in inventory for both parties
        if (!(validTrade(trade))) {
            throw new InvalidTradeException("Trade is no longer valid");
        }

        User borrower = dataManager.retrieveUser(trade.getBorrower());
        User owner = dataManager.retrieveUser(trade.getOwner());

        // remove from pendingList
        owner.getPendingTrades().delete(trade.getUniqueID());
        borrower.getPendingTrades().delete(trade.getUniqueID());

        // add to pastTradesList
        owner.addPastTrade(trade);
        borrower.addPastTrade(trade);

        // save users
        dataManager.saveUser(borrower);
        dataManager.saveUser(owner);

        dataManagerLocal.saveUser(borrower);
        dataManagerLocal.saveUser(owner);

        // save userSingleton
        UserSingleton.addCurrentUser(owner);
    }

    /**
     *
     * Handles the case if a user does not like the terms of a trade
     * and wishes to propose a counter trade
     *
     * @param context
     * @param trade
     */

    public void counterPendingTrade(Context context, Trade trade){
        // Saving done in deleteTrade
        deletePendingTrade(trade);

        Intent intent = new Intent(context, FeedTradeActivity.class);
        intent.putExtra("Username", trade.getOwner());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     *
     * Handles the confirmation of a borrow type trade:
     *  vehicles are private and not tradeable, and the trade
     *  remains pending until the vehicle is returned.
     *
     * @param trade
     * @throws InvalidTradeException
     */

    public void confirmBorrowTrade(Trade trade) throws InvalidTradeException {

        User borrower = dataManager.retrieveUser(trade.getBorrower());
        User owner = dataManager.retrieveUser(trade.getOwner());



        // save users
        dataManager.saveUser(borrower);
        dataManager.saveUser(owner);

        dataManagerLocal.saveUser(borrower);
        dataManagerLocal.saveUser(owner);

        // save userSingleton
        UserSingleton.addCurrentUser(borrower);
    }

    public TradeList getActiveTrades(){
        return UserSingleton.getCurrentUser().getPendingTrades();
    }

    public TradeList getPastTrades() {
        return UserSingleton.getCurrentUser().getPastTrades();
    }


    /**
     *
     * Checks validity of a trade.
     *
     * @param trade
     * @return
     */

    public boolean validTrade(Trade trade) {
        ArrayList<Vehicle> borrower = dataManager.retrieveUser(trade.getBorrower()).getInventory().getList();
        ArrayList<Vehicle> owner = dataManager.retrieveUser(trade.getOwner()).getInventory().getList();

        Boolean o = validVehicles(trade.getOwnerItems(), owner);
        Boolean b = validVehicles(trade.getBorrowerItems(), borrower);

        Boolean c = o && b;

        return c;
    }

    /**
     * Checks if the vehicles are valid and available.
     *
     * @param tradeVehicles
     * @param inventoryVehicles
     * @return
     */

    public Boolean validVehicles(ArrayList<Vehicle> tradeVehicles, ArrayList<Vehicle> inventoryVehicles) {

        for (Vehicle tradeVehicle : tradeVehicles) {
            Boolean vehicleInInventory = false;
            for (Vehicle inventoryVehicle: inventoryVehicles) {
                if (tradeVehicle.getUniqueID().isEqualID(inventoryVehicle.getUniqueID())) {
                    vehicleInInventory = true;
                    break;
                }

            }
            if (!(vehicleInInventory)) {
                return false;
            }
        }
        return true;
    }


}
