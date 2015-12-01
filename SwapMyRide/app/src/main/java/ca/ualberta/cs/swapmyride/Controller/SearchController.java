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

import android.app.Activity;
import android.content.Context;
import android.location.Address;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Model.Geolocation;
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
     *
     * @param username
     * @param context
     * @return
     */
    public ArrayList<User> findUser(String username, Context context) {
        //TODO: Search the server for a user that matches the given username
        NetworkDataManager dataManager = new NetworkDataManager();

        ArrayList<User> userList = new ArrayList<>();

        if (dataManager.searchUser(username)) {
            userList.add(dataManager.retrieveUser(username));
        }

        return userList;
    }

    /**
     * FindVehicle takes a vehicleName and searches storage for a vehicle that
     * matches. Returns the Vehicle if found.
     *
     * @param vehicleName
     * @return
     */
    public Vehicle findVehicle(String vehicleName) {
        //TODO: Search the server for a vehicle that matches the given vehicleName
        return new Vehicle();
    }

    /**
     * Returns an ArrayList of vehicles based on name and/or category that is passed
     * to the function.
     *
     * @param vehicleName
     * @param vehicleCategory
     * @return
     */

    public ArrayList<Vehicle> findInventoryVehicle(String vehicleName, VehicleCategory vehicleCategory,
                                                   Activity activity, Context context, double distance) {

        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        ArrayList<Vehicle> userVehicles = UserSingleton.getCurrentUser().getInventory().getList();

        int size = userVehicles.size();

        Geolocation geolocation = new Geolocation();
        Address address = geolocation.getCurrentLocation(context, activity);

        // search both
        if (!(vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getName().equals(vehicleName))
                    if (userVehicles.get(i).getCategory().equals(vehicleCategory)) {
                        if (withinDistance(distance, userVehicles.get(i).getLocation().getLatitude(),
                                userVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                                address.getLongitude())) {
                            foundVehicles.add(userVehicles.get(i));
                        }
                    }
            }
        }

        // Search string only
        else if (!(vehicleName.equals("")) && (vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getName().equals(vehicleName)) {
                    if (withinDistance(distance, userVehicles.get(i).getLocation().getLatitude(),
                            userVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                            address.getLongitude())) {
                        foundVehicles.add(userVehicles.get(i));
                    }
                }
            }
        }

        // Search category only
        else if ((vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (userVehicles.get(i).getCategory().equals(vehicleCategory)) {
                    if (withinDistance(distance, userVehicles.get(i).getLocation().getLatitude(),
                            userVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                            address.getLongitude())) {
                        foundVehicles.add(userVehicles.get(i));
                    }
                }

            }
        }

        // Default return value
        return foundVehicles;
    }

    /**
     * Returns an ArrayList of vehicles based on name and/or category that is passed
     * to the function.
     *
     * @param vehicleName
     * @param vehicleCategory
     * @return
     */

    public ArrayList<Vehicle> findFeedVehicle(String vehicleName, VehicleCategory vehicleCategory,
                                              Activity activity, Context context, double distance) {


        UserController userController = new UserController(context);

        ArrayList<Vehicle> foundVehicles = new ArrayList<>();

        ArrayList<Vehicle> friendsVehicles = userController.getFriendVehicles().getList();

        int size = friendsVehicles.size();

        Geolocation geolocation = new Geolocation();
        Address address = geolocation.getCurrentLocation(context, activity);

        // search both
        if (!(vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (friendsVehicles.get(i).getName().equals(vehicleName)) {
                    if (friendsVehicles.get(i).getCategory().equals(vehicleCategory)) {
                        if (withinDistance(distance, friendsVehicles.get(i).getLocation().getLatitude(),
                                friendsVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                                address.getLongitude())) {
                            foundVehicles.add(friendsVehicles.get(i));
                        }
                    }
                }
            }
        }

        // Search string only
        else if (!(vehicleName.equals("")) && (vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (friendsVehicles.get(i).getName().equals(vehicleName)) {
                    if (withinDistance(distance, friendsVehicles.get(i).getLocation().getLatitude(),
                            friendsVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                            address.getLongitude())) {
                        foundVehicles.add(friendsVehicles.get(i));
                    }
                }
            }
        }

        // Search category only
        else if ((vehicleName.equals("")) && !(vehicleCategory.equals(VehicleCategory.NONE))) {

            for (int i = 0; i < size; i++) {

                if (friendsVehicles.get(i).getCategory().equals(vehicleCategory)) {
                    if (withinDistance(distance, friendsVehicles.get(i).getLocation().getLatitude(),
                            friendsVehicles.get(i).getLocation().getLongitude(), address.getLatitude(),
                            address.getLongitude())) {
                        foundVehicles.add(friendsVehicles.get(i));
                    }
                }

            }
        }

        // Default return value
        return foundVehicles;
    }

    /**
     * Calculates to find out if a car is within the prescribed distance from the device's
     * current location
     *
     * @param distance
     * @param latCar
     * @param longCar
     * @param lat
     * @param longit
     * @return True if vehicle is within the specified distance
     * <p/>
     * Adapted from:
     * http://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
     * <p/>
     * User: Deduplicator / Chuck        Accessed: 28/11/2015
     */

    public Boolean withinDistance(double distance, double latCar, double longCar, double lat, double longit) {

        if (distance == 0.0) {
            return true;
        }

        double diff;
        double r = 6371; // Radius of the earth in KM
        double dLat = Math.toRadians(lat - latCar);
        double dLong = Math.toRadians(longit - longCar);
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(Math.toRadians(latCar)) * Math.cos(Math.toRadians(lat)) *
                Math.pow(Math.sin(dLong / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        diff = r * c;

        return diff <= distance;

    }
}
