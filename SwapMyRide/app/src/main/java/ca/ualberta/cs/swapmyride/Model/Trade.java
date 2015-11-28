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
package ca.ualberta.cs.swapmyride.Model;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UniqueID;

/**
 * Trade is the major object to encapsulate all
 * information about a trade that will occur
 *
 * All relevant information to the trade will be held here.
 *
 * Owner items belong to the current user
 * Borrower items are items that belong to a friend.
 *
 * @author adrianomarini on 2015-10-26.
 */
public class Trade {
    private String owner;
    private String borrower;
    private ArrayList<Vehicle> ownerItems = new ArrayList<>();
    private ArrayList<Vehicle> borrowerItems = new ArrayList<>();
    private Boolean ownerNotified = false;
    private Boolean borrowerNotified = false;
    private Boolean isAccepted = false;
    private Boolean isDeclined = false;
    private UniqueID uniqueID = new UniqueID();

    /**
     * Constructs the trade with indication of who the two users
     * participating in the app are.
     */

    public Trade() {
        owner = "";
        borrower = "";
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public ArrayList<Vehicle> getBorrowerItems() {
        return borrowerItems;
    }

    public void setBorrowerItems(ArrayList<Vehicle> borrowerItems) {
        this.borrowerItems = borrowerItems;
    }

    public ArrayList<Vehicle> getOwnerItems() {
        return ownerItems;
    }

    public void setOwnerItems(ArrayList<Vehicle> ownerItems) {
        this.ownerItems = ownerItems;
    }

    public void addBorrowerItem(Vehicle vehicle){
        borrowerItems.add(vehicle);
    }

    public void addOwnerItem(Vehicle vehicle){
        ownerItems.add(vehicle);
    }

    public void clearOwnerItems() {
        this.ownerItems.clear();
    }

    public void clearBorrowerItems() {
        this.borrowerItems.clear();
    }

    /**
     * sends notifications to the borrower and owner, and then adds the trade to
     * the pending trades of each user.
     */

    public void send(){
        //borrowerNotified = borrower.getNotificationManager().notifyTrade(this);
        //owner.addPendingTrade(this);
        //borrower.addPendingTrade(this);
    }

    public Boolean getOwnerNotified() {
        return ownerNotified;
    }

    public Boolean getBorrowerNotified() {
        return borrowerNotified;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    /**
     * Accept allows a trade to be accepted and implements the functionality
     * to send an email with more information to all parties.
     */

    public void accept(){
        this.isAccepted = true;
        //here is where we would send an email
        //// TODO: ADD EMAIL FUNCTIONALITY 
    }

    public Boolean getIsDeclined() {
        return isDeclined;
    }

    /**
     * accept allows a user to decline a trade
     */

    public void decline(){
        this.isDeclined = true;
    }

    /**
     * if a trade is declined, a new trade can be initialized with
     * the same items from both sides pending changes
     * @return Trade with roles swapped
     */

    public Trade makeCounterTrade(){
        Trade counterTrade = new Trade();
        counterTrade.setOwner(this.owner);
        counterTrade.setBorrower(this.borrower);
        counterTrade.setBorrowerItems(this.getOwnerItems());
        counterTrade.setOwnerItems(this.getBorrowerItems());
        counterTrade.send();
        return counterTrade;
    }

    /**
     * Functionality to swap out vehicles in a trade if needed
     * @param old
     * @param newOne
     */

    public void changeOwnerVehicle(Vehicle old, Vehicle newOne){
        this.ownerItems.remove(old);
        ownerItems.add(newOne);
    }

    /**
     * Functionality to swap out vehicles in a trade if needed
     * @param old
     * @param newOne
     */

    public void changeBorrowerVehicle(Vehicle old, Vehicle newOne){
        this.borrowerItems.remove(old);
        borrowerItems.add(newOne);
    }

    public void setOwnerNotified(Boolean ownerNotified) {
        this.ownerNotified = ownerNotified;
    }

    public void setBorrowerNotified(Boolean borrowerNotified) {
        this.borrowerNotified = borrowerNotified;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public void setIsDeclined(Boolean isDeclined) {
        this.isDeclined = isDeclined;
    }

    public UniqueID getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(UniqueID uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Trade copy(){
        Trade newTrade = new Trade();

        newTrade.setOwner(this.getOwner());
        newTrade.setBorrower(this.getBorrower());
        newTrade.setOwnerItems(this.getOwnerItems());
        newTrade.setBorrowerItems(this.getBorrowerItems());
        newTrade.setOwnerNotified(this.getOwnerNotified());
        newTrade.setBorrowerNotified(this.getBorrowerNotified());
        newTrade.setIsAccepted(this.getIsAccepted());
        newTrade.setIsDeclined(this.getIsDeclined());
        newTrade.setUniqueID(this.getUniqueID().duplicateID());

        return newTrade;
    }

    public void swapBelongsTo() {
        for (Vehicle vehicle: ownerItems) {
            vehicle.setBelongsTo(borrower);
        }

        for (Vehicle vehicle: borrowerItems) {
            vehicle.setBelongsTo(owner);
        }
    }
}
