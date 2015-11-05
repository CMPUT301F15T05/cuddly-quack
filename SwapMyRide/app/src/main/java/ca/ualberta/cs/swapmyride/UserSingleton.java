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
 * Created by adrianomarini on 2015-11-01.
 */
public class UserSingleton {
    private static User currentUser = null;
    private static ArrayList<User> friends = new ArrayList<>();

    private static UserSingleton ourInstance = new UserSingleton();

    public static UserSingleton getInstance() {
        return ourInstance;
    }

    public static void addCurrentUser(User user){
        currentUser = user;
    }

    public static User getCurrentUser(){
        //TODO temporary fix, a user should always be created through login
        if(currentUser == null){
            return new User();
        }
        return currentUser;
    }

    public static void addFriends(User user){
        friends.add(user);
    }

    public static ArrayList<User> getFriends(){
        return friends;
    }

    public static void setFriends(ArrayList<User> users){
        friends = users;
    }
}
