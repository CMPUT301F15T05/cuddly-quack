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

import android.util.Log;

/**
 * Created by Garry on 2015-11-01.
 */
public class UserController {
    private static UserSingleton thisSingleton = UserSingleton.getInstance();

    //for the time being, these classes access the local user list to verify
    //TODO: implenment remote database usage for userExists, addCurrentUser, addUser
    public static boolean userExists(String username){
        String tempString;
        for(User user : thisSingleton.getUsers()){
            tempString = user.getUserName();
            Log.i("userExits", tempString);
            if(tempString.equals(username) /*&& tempString.length() == username.length()*/){
                return true;
            }
        }
        return false;
    }

    //adds current user to the contextual variables for the usage
    public static void addCurrentUser(String username){
        String tempString;
        User currentUser = new User();
        for(User user : UserSingleton.getUsers()) {
            tempString = user.getUserName();
            if (tempString.equals(username)) {
                currentUser = user;
            }
        }
        UserSingleton.addCurrentUser(currentUser);
    }

    //adds user to user list
    public static boolean addUser(User user){
        UserSingleton.addUser(user);
        return true;
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

    public void addFriend(User toAdd){
        UserSingleton.getCurrentUser().addFriend(toAdd);
    }

    public void deleteFriend(User toDelete){
        UserSingleton.getCurrentUser().removeFriend(toDelete);
    }

}
