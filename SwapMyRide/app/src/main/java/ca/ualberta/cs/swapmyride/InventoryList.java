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
 * InventoryList is the object that holds an array of vehicles
 * that belong to a specific user.
 */

public class InventoryList {
    private ArrayList<Vehicle> vehicleList;

    /**
     * Constructor - takes no arguments
     * Creates the array of vehicles.
     */

    public InventoryList(){
        vehicleList = new ArrayList<>();
    }

    /**
     * Constructor in the case where an array has been created.
     * Takes in the array and sets it as the global vehicleList
     * @param vehicleList
     */
    public InventoryList(ArrayList<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }

    /**
     * Returns an array list of the vehicles contained in the object
     * @return ArrayList with Vehicles
     */

    public ArrayList<Vehicle> getList(){
        return vehicleList;
    }

    /**
     * Adds a vehicle that is passed to the inventory list
     * @param vehicle
     */

    public void add(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    /**
     * Deletes specific vehicle that is passed from the
     * inventory list
     * @param vehicle
     */

    public void delete(Vehicle vehicle){
        vehicleList.remove(vehicle);
    }

    /**
     * Returns the size of the inventory
     * @return size
     */
    public int size(){
        return vehicleList.size();
    }

    /**
     * Returns an array list of all vehicles that are
     * declared public from the inventory
     *
     * Useful when creating list of publicly available items
     *
     * @return ArrayList with vehicles
     */

    public ArrayList<Vehicle> getPublic(){
        ArrayList<Vehicle> publicVehicles = new ArrayList<>();
        for (Vehicle vehicle: vehicleList) {
            if(vehicle.getPublic()){
                publicVehicles.add(vehicle);
            }
        }
        return publicVehicles;
    }

    /**
     * Returns an ArrayList of all the vehicles that are declared
     * as private from the inventory.
     *
     * Useful when creating list of private items
     *
     * @return ArrayList with vehicles
     */

    public ArrayList<Vehicle> getPrivate(){
        ArrayList<Vehicle> publicVehicles = new ArrayList<>();
        for (Vehicle vehicle: vehicleList) {
            if(!vehicle.getPublic()){
                publicVehicles.add(vehicle);
            }
        }
        return publicVehicles;
    }

    /**
     * Given a name of a vehicle, searches inventory to see if
     * said vehicle exists.
     *
     * @param vehicleName
     * @return True if found | False if not found
     */
    public boolean search(String vehicleName){
        for (Vehicle vehicle: vehicleList) {
            if(vehicle.getName().equals(vehicleName)) {
                return true;
            }
        }
        //default to return false
        return false;
    }

    /**
     * Returns an InventoryList of vehicles of a specific category
     * that are contained within the inventory
     *
     * Useful when searching by category/
     *
     * @param category
     * @return InventoryList.
     */

    public InventoryList getCategory(VehicleCategory category){
        InventoryList newInventory = new InventoryList();
        for (Vehicle vehicle: vehicleList){
            if(vehicle.getCategory().equals(category)){
                newInventory.add(vehicle);
            }
        }
        return newInventory;
    }


}
