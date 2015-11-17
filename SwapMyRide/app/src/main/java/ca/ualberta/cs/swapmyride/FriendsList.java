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
 * FriendsList is an object that holds a series of usernames
 * which are indicated to be friends of a certain user,
 * and provides an easy way to access and mutate this data
 *
 * @author Garry Bullock on 2015-10-26.
 */
public class FriendsList {
    private ArrayList<String> friendList;

    /**
     * Constructor - takes no inputs but initializes the object.
     */
    public FriendsList(){
        friendList = new ArrayList<>();
    }

    /**
     * Taking a username, add this person to a user's friends
     * @param username
     */
    public void addFriend(String username){
        friendList.add(username);
    }

    /**
     * Taking a username, remove this person from a user's friends.
     * @param username
     */
    public void removeFriend(String username){
        friendList.remove(username);
    }

    /**
     * @return Size of FriendsList
     */
    public int size(){return friendList.size();}

    /**
     * Taking a username, attempts to find username in list
     * @param username
     * @return True if found | False if not found
     */
    public boolean findUser(String username){
        return friendList.contains(username);
    }

    /**
     * Similar to the above, but instead taking an input of a user object
     * The User object is converted to a string and the string is searched.
     * @param user
     * @return True if found | False if not found
     */
    public boolean hasUser(User user){
        String username = user.getUserName();
        return friendList.contains(username);
    }

    /**
     * Simple getter, returning the array list if it needs to be iterated through.
     * @return friendList
     */
    public ArrayList<String> getFriendList() {
        return friendList;
    }
}
