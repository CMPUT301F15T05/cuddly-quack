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

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;

/**
 * UserController is the main access point between the Views and the user models.
 */
public class UserController {
    Context context;
    DataManager dm;

    public UserController(Context context){
        this.context = context;
        dm = new DataManager(context);
    }

    /**
     * updateFriends gets the friendList of the current user (whoever is logged in),
     * and ensures their most recent data is accessible by the UserController.
     * The usernames are read from the friends list, they are then passed to the LocalDataManager which
     * loads the username and returns a user. This user is stored in the UserSingleton in a list
     * of users.
     */
    public void updateFriends(){
        ArrayList<String> friends = UserSingleton.getCurrentUser().getFriends().getFriendList();
        ArrayList<User> users = new ArrayList<>();
        User newUser;
        for (String friend : friends) {
            newUser = dm.loadUser(friend);
            users.add(newUser);
        }
        UserSingleton.setFriends(users);
    }

    //for the time being, these classes access the local user list to verify
    //TODO: implenment remote database usage for userExists, addCurrentUser, addUser

    /**
     * userExists sends the username to LocalDataManager which checks to see if the user exists in
     * the internal file system.
     * @param username
     * @return true: User exists, false: User does not
     */
    public boolean userExists(String username){
        return dm.searchUserLocal(username);
    }

    /**
     * Stores the given user into the "Active" user inside UserSingleton for access.
     * This version takes a username, loads it from the LocalDataManager, and then stores the returned
     * user into the Singleton.
     * @param username
     */
    public void addCurrentUser(String username){
        User user = dm.loadUser(username);
        UserSingleton.addCurrentUser(user);
        updateFriends();
    }

    /**
     * Stores the given user into the "Active" user inside UserSingleton for access.
     * This version takes a user, and then stores it directly into the Singleton.
     * user into the Singleton.
     * @param user
     */
    public void addCurrentUser(User user){
        UserSingleton.addCurrentUser(user);
        updateFriends();
    }

    /**
     * Adds a user to the "active" user's friendlist.
     * @param user
     */
    //adds user to user list
    public void addFriend(User user){
        UserSingleton.addFriends(user);
    }

    /**
     * Gets the "Active" users friends from the singleton
     * @return ArrayList
     */
    public ArrayList<User> getFriends(){
        return UserSingleton.getFriends();
    }

    /**
     * Gets the "Active" user from the singleton.
     * @return
     */
    public User getCurrentUser(){
        return UserSingleton.getCurrentUser();
    }

    /**
     * Gets the inventory of a given user.
     * @param user
     * @return
     */
    public InventoryList getInventory(User user){
        //TODO: Get the inventory of a given user
        InventoryList inventory;
        User currentUser = getCurrentUser();
        inventory = currentUser.getInventory();
        return inventory;
    }

    /**
     * Will change the download preferance of the "Active" user to the given boolean
     * @param preference
     */
    public void changeDownloadPreferance(boolean preference){
        //TODO:Switch the prefrance of the current user
        UserSingleton.getCurrentUser().setDownloadImages(preference);
    }


    /**
     * Adds the user identified by the username given in toAdd.
     * @param toAdd
     */
    public void addFriend(String toAdd){
        UserSingleton.getCurrentUser().addFriend(toAdd);
        UserSingleton.addFriends(dm.loadUser(toAdd));
    }

    /**
     * deletes a friend from the "active" user, and then updates the friends in UserSingleton
     * @param toDelete
     */
    public void deleteFriend(String toDelete){
        UserSingleton.getCurrentUser().removeFriend(toDelete);

        //TODO Is this as efficient as it could be??
        updateFriends();
    }

    /**
     * Gets all vehicles from the "Active" user.
     * @return
     */
    public ArrayList<Vehicle> getUserInventoryItems(){
        return UserSingleton.getCurrentUser().getInventory().getList();
    }

    /**
     * Uses LocalDataManager to save the "Active" user.
     */
    public void saveCurrentUser(){
        dm.saveUser(UserSingleton.getCurrentUser());
    }

    /**
     * Gets all vehicles of the current friends of the "Active" user
     * @return
     */
    public InventoryList getFriendVehicles(){
        InventoryList inventoryList = new InventoryList();
        for (User friend : getFriends()) {
            InventoryList friendInventory = friend.getInventory();

            for (Vehicle vehicle : friendInventory.getList()) {
                if (vehicle.getPublic()) {
                    inventoryList.add(vehicle);
                }
            }
        }
        return inventoryList;
    }

    /**
     * Gets vehicles of a specified friend of the "Active" user
     * @return
     */
    public InventoryList getFriendVehicles(String username){
        InventoryList inventoryList = new InventoryList();
        for (User friend : getFriends()) {

            if (friend.getUserName().equals(username)) {
                InventoryList friendInventory = friend.getInventory();

                for (Vehicle vehicle : friendInventory.getList()) {
                    if (vehicle.getPublic()) {
                        inventoryList.add(vehicle);
                    }
                }
            }
        }
        return inventoryList;
    }
}
