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

import java.util.ArrayList;

/**
 * Created by Garry on 2015-10-26.
 */
public class FriendsList {
    private ArrayList<User> friendList;

    public FriendsList(){
        friendList = new ArrayList<>();
    }

    public void addFriend(User user){
        friendList.add(user);
    }

    public void removeFriend(User user){
        friendList.remove(user);
    }

    public int size(){return friendList.size();}

    public User findUser(User user){
        //based on the test for Use Case 8, we should search through the list and see if
        //any of the objects matches the one given, and return.
        for (User userInList: friendList) {
            if(userInList.equals(user)){
                return userInList;
            }
        }
        //if we dont find the user we are looking for, i'm not sure what to return??
        return null;
    }

    public boolean hasUser(User user){
        return friendList.contains(user);
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }
}
