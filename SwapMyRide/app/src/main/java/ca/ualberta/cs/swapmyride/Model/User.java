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

/**
 * This Class stores all necessary information pertaining to the user for storage and access.
 * It provides the foundation for all functionality of the application.
 *
 */
public class User {
    private String name;
    private String userName;
    private String userEmail;
    private String userAddress;
    private InventoryList inventory;
    private FriendsList friends;
    private boolean downloadImages;
    private TradeList pastTrades;
    private TradeList pendingTrades;
    private NotificationManager notificationManager;
    //private Setting settings;

    //this constructor is used for creating a new name
    public User(){
        //userName = "";
        inventory = new InventoryList();
        friends = new FriendsList();
        pastTrades = new TradeList();
        pendingTrades = new TradeList();
        notificationManager = new NotificationManager();
        //settings = new Setting();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void addItem(Vehicle vehicle){
        inventory.add(vehicle);
    }

    public InventoryList getInventory(){
        return inventory;
    }

    public Boolean getDownloadImages() {
        return downloadImages;
    }

    public void setDownloadImages(boolean downloadImages) {
        this.downloadImages = downloadImages;
    }

    public FriendsList getFriends() {
        return friends;
    }

    public void addFriend(String username){
        friends.addFriend(username);
    }

    public void removeFriend(String username){
        friends.removeFriend(username);
    }

    public TradeList getPastTrades() {
        return pastTrades;
    }

    public void addPastTrade(Trade pastTrade) {
        pastTrades.add(pastTrade);
    }

    public TradeList getPendingTrades() {
        return pendingTrades;
    }

    public void addPendingTrade(Trade pendingTrade) {
        pendingTrades.add(pendingTrade);
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setPendingTrades(TradeList pendingTrades) {
        this.pendingTrades = pendingTrades;
    }

    public void setPastTrades(TradeList pastTrades) {
        this.pastTrades = pastTrades;
    }

    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (other.getClass() != this.getClass())return false;
        User test = (User)other;
        if(!this.name.equals(test.getName())) return false;
        if(!this.userName.equals(test.getUserName())) return false;
        if(!this.userEmail.equals(test.getUserEmail())) return false;
        if(!this.userAddress.equals(test.getUserAddress())) return false;
        if(!this.userName.equals(test.getUserName())) return false;
        if(!this.getInventory().getList().equals(test.getInventory().getList()))return false;
        if(!this.getFriends().getFriendList().equals(test.getFriends().getFriendList())) return false;
        if(this.downloadImages != test.getDownloadImages()) return false;
        if(!this.getPendingTrades().getTrades().equals(test.getPendingTrades().getTrades())) return false;
        if(!this.getPastTrades().getTrades().equals(test.getPastTrades().getTrades())) return false;

        return true;
    }
}
