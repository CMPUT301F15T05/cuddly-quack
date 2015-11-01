package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-11-01.
 */
public class SearchController {

    public User findUser(String username){
        //TODO: Search the server for a user that matches the given username
        return UserSingleton.getCurrentUser();
    }

    public Vehicle findVehicle(String vehicleName){
        //TODO: Search the server for a vehicle that matches the given vehicleName
        return new Vehicle();
    }


}
