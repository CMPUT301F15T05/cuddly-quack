package ca.ualberta.cs.swapmyride;

import java.util.ArrayList;

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

    public ArrayList<Vehicle> findInventoryVehicle(String vehicleName, VehicleCategory vehicleCategory, ArrayList<Vehicle> userVehicles) {

        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        int size = userVehicles.size();

        for (int i = 0; i < size; i++) {

            if (userVehicles.get(i).getName().equals(vehicleName))
                if (userVehicles.get(i).getCategory().equals(vehicleCategory)) {
                    foundVehicles.add(userVehicles.get(i));
            }
        }
        return foundVehicles;
    }
}
