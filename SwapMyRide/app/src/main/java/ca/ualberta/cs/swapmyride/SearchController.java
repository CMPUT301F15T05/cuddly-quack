package ca.ualberta.cs.swapmyride;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Garry on 2015-11-01.
 */
public class SearchController {

    public ArrayList<User> findUser(String username, Context context){
        //TODO: Search the server for a user that matches the given username
        DataManager dataManager = new DataManager(context);

        ArrayList<User> userList = new ArrayList<>();

        if (dataManager.searchUser(username)) {
            userList.add(dataManager.loadUser(username));
        }

        return userList;
    }

    public Vehicle findVehicle(String vehicleName){
        //TODO: Search the server for a vehicle that matches the given vehicleName
        return new Vehicle();
    }

    public ArrayList<Vehicle> findInventoryVehicle(String vehicleName, VehicleCategory vehicleCategory, ArrayList<Vehicle> userVehicles) {

        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        int size = userVehicles.size();

        // search both
        if (!(vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getName().equals(vehicleName))
                    if (userVehicles.get(i).getCategory().equals(vehicleCategory)) {
                        foundVehicles.add(userVehicles.get(i));
                    }
            }
        }

        // Search string only
        else if (!(vehicleName.equals("")) && (vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getName().equals(vehicleName))
                    foundVehicles.add(userVehicles.get(i));
            }
        }

        // Search category only
        else if ((vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))){

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getCategory().equals(vehicleCategory))
                    foundVehicles.add(userVehicles.get(i));
            }
        }

        // Default return value
        return foundVehicles;
    }
}
