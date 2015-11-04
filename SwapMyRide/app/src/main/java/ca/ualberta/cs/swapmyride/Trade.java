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

import android.graphics.Picture;

import java.util.ArrayList;

/**
 * Created by adrianomarini on 2015-10-26.
 */
public class Trade {
    private User owner = new User();
    private User borrower = new User();
    private ArrayList<Vehicle> ownerItems = new ArrayList<>();
    private ArrayList<Vehicle> borrowerItems = new ArrayList<>();
    private Boolean ownerNotified = false;
    private Boolean borrowerNotified = false;
    private Boolean isAccepted = false;
    private Boolean isDeclined = false;

    public Trade(User owner, User borrower) {
        this.owner = owner;
        this.borrower = borrower;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
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

    public void send(){
        ownerNotified = true;
        borrowerNotified = true;
    }

    public Boolean getOwnerNotified() {
        return ownerNotified;
    }

    public void setOwnerNotified(Boolean ownerNotified) {
        this.ownerNotified = ownerNotified;
    }

    public Boolean getBorrowerNotified() {
        return borrowerNotified;
    }

    public void setBorrowerNotified(Boolean borrowerNotified) {
        this.borrowerNotified = borrowerNotified;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void accept(){
        this.isAccepted = true;
    }

    public Boolean getIsDeclined() {
        return isDeclined;
    }

    public void decline(){
        this.isDeclined = true;
    }

    public Trade makeCounterTrade(){
        Trade counterTrade = new Trade(this.borrower, this.owner);
        counterTrade.setBorrowerItems(this.getOwnerItems());
        counterTrade.setOwnerItems(this.getBorrowerItems());
        counterTrade.send();
        return counterTrade;
    }

    public void changeOwnerVehicle(Vehicle old, Vehicle newOne){
        this.ownerItems.remove(old);
        ownerItems.add(newOne);
    }

    public void changeBorrowerVehicle(Vehicle old, Vehicle newOne){
        this.borrowerItems.remove(old);
        borrowerItems.add(newOne);
    }

}
