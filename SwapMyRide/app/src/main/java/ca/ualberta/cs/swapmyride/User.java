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

/**
 * Created by Garry on 2015-10-26.
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
    public NotificationManager notificationManager;
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

    public void addFriend(User user){
        friends.addFriend(user);
    }

    public void removeFriend(User user){
        friends.removeFriend(user);
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
}
