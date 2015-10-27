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
}
