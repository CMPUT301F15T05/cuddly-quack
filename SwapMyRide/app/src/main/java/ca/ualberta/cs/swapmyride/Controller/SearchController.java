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
package ca.ualberta.cs.swapmyride.Controller;

import android.content.Context;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;

/**
 * SearchController is designed to manage the search functionality
 * of the app. Has methods to search data that exists in the objects
 * and return it in usable form to activities
 *
 * @author Created by Garry on 2015-11-01.
 */
public class SearchController {

    /**
     * Taking a username in, searches the data located on disk for any instance
     * matching the username and returns an ArrayList with matching users.
     * @param username
     * @param context
     * @return
     */
    public ArrayList<User> findUser(String username, Context context){
        //TODO: Search the server for a user that matches the given username
        DataManager dataManager = new DataManager(context);

        ArrayList<User> userList = new ArrayList<>();

        if (dataManager.searchUserServer(username)) {
            userList.add(dataManager.loadUser(username));
        }

        return userList;
    }


    /**
     * FindVehicle takes a vehicleName and searches storage for a vehicle that
     * matches. Returns the Vehicle if found.
     * @param vehicleName
     * @return
     */
    public Vehicle findVehicle(String vehicleName){
        //TODO: Search the server for a vehicle that matches the given vehicleName
        return new Vehicle();
    }

    /**
     * Returns an ArrayList of vehicles based on name and/or category that is passed
     * to the function.
     * @param vehicleName
     * @param vehicleCategory
     * @param userVehicles
     * @return
     */

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

    /**
     * Returns an ArrayList of vehicles based on name and/or category that is passed
     * to the function.
     * @param vehicleName
     * @param vehicleCategory
     * @param userVehicles
     * @return
     */

    public ArrayList<Vehicle> findInventoryVehicleDistance(String vehicleName, VehicleCategory vehicleCategory,
                                                           ArrayList<Vehicle> userVehicles, int distance) {

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
