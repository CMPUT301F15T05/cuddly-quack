package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */
public class User {
    private String userName;
    private InventoryList inventory;
    private FriendsList friends;
    private TradeList pastTrades;
    private TradeList pendingTrades;
    private Setting settings;

    //this constructor is used for creating a new user
    public User(){
        userName = "";
        inventory = new InventoryList();
        friends = new FriendsList();
        pastTrades = new TradeList();
        pendingTrades = new TradeList();
        settings = new Setting();
    }

    public void addUser(String name){
        userName = name;
    }



}
