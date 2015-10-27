package ca.ualberta.cs.swapmyride;

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
        //don't know what goes here yet
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

    public Trade makeCounterTrade(Trade trade){
        Trade counterTrade = new Trade(this.borrower, this.owner);
        // don't know what goes here yet
        return counterTrade;
    }

    public void changeOwnerVehicle(Vehicle old, Vehicle newOne){
        int index = this.ownerItems.indexOf(old);
        this.ownerItems.remove(index);
        ownerItems.add(newOne);
    }

    public void changeBorrowerVehicle(Vehicle old, Vehicle newOne){
        int index = this.ownerItems.indexOf(old);
        this.ownerItems.remove(index);
        ownerItems.add(newOne);
    }

}
