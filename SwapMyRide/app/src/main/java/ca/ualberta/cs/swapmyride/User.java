package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */
public class User {
    private String user;
    private String userName;
    private String userEmail;
    private String userAddress;
    private InventoryList inventory;
    private FriendsList friends;
    private boolean downloadImages;
    private TradeList pastTrades;
    private TradeList pendingTrades;
    //private Setting settings;

    //this constructor is used for creating a new user
    public User(){
        //userName = "";
        inventory = new InventoryList();
        friends = new FriendsList();
        pastTrades = new TradeList();
        pendingTrades = new TradeList();
        //settings = new Setting();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
