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

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Garry on 2015-11-01.
 */
public class UserController {
    private static UserSingleton thisSingleton = UserSingleton.getInstance();
    Context context;
    DataManager dm;

    UserController(Context context){
        this.context = context;
        dm = new DataManager(context);
    }

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
    public boolean userExists(String username){
        DataManager dataManager = new DataManager(context);
        return dataManager.searchUser(username);
    }

    //adds current user to the contextual variables for the usage
    public void addCurrentUser(String username){
        DataManager dataManager = new DataManager(context);
        User user = dataManager.loadUser(username);
        UserSingleton.addCurrentUser(user);
        updateFriends();
    }

    public void addCurrentUser(User user){
        UserSingleton.addCurrentUser(user);
        updateFriends();
    }

    //adds user to user list
    public void addFriend(User user){
        UserSingleton.addFriends(user);
    }

    public ArrayList<User> getFriends(){
        return UserSingleton.getFriends();
    }

    public User getCurrentUser(){
        return UserSingleton.getCurrentUser();
    }

    public InventoryList getInventory(User user){
        //TODO: Get the inventory of a given user
        InventoryList inventory;
        User currentUser = getCurrentUser();
        inventory = currentUser.getInventory();
        return inventory;
    }

    public void changeDownloadPreferance(boolean preference){
        //TODO:Switch the prefrance of the current user
        UserSingleton.getCurrentUser().setDownloadImages(preference);
    }

    public FriendsList getFriends(User user){
        return user.getFriends();
    }

    public void addFriend(String toAdd){
        UserSingleton.getCurrentUser().addFriend(toAdd);
        DataManager dm = new DataManager(context);
        UserSingleton.addFriends(dm.loadUser(toAdd));
    }

    public void deleteFriend(String toDelete){
        UserSingleton.getCurrentUser().removeFriend(toDelete);
        updateFriends();
    }

    public ArrayList<Vehicle> getUserInventoryItems(){
        return UserSingleton.getCurrentUser().getInventory().getList();
    }

    public void saveCurrentUser(){
        DataManager dm = new DataManager(context);
        dm.saveUser(UserSingleton.getCurrentUser());
    }
}
