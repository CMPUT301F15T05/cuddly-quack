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

public class InventoryList {
    private ArrayList<Vehicle> vehicleList;

    public InventoryList(){
        vehicleList = new ArrayList<Vehicle>();
    }

    public InventoryList(ArrayList<Vehicle> vehicleList){
        this.vehicleList = vehicleList;
    }

    public ArrayList<Vehicle> getList(){
        return vehicleList;
    }

    public void add(Vehicle vehicle){
        vehicleList.add(vehicle);
    }

    public void delete(Vehicle vehicle){
        vehicleList.remove(vehicle);
    }

    public int size(){
        return vehicleList.size();
    }

    public ArrayList<Vehicle> getPublic(){
        ArrayList<Vehicle> publicVehicles = new ArrayList<>();
        for (Vehicle vehicle: vehicleList) {
            if(vehicle.getPublic()){
                publicVehicles.add(vehicle);
            }
        }
        return publicVehicles;
    }

    public ArrayList<Vehicle> getPrivate(){
        ArrayList<Vehicle> publicVehicles = new ArrayList<>();
        for (Vehicle vehicle: vehicleList) {
            if(!vehicle.getPublic()){
                publicVehicles.add(vehicle);
            }
        }
        return publicVehicles;
    }

    public boolean search(String vehicleName){
        for (Vehicle vehicle: vehicleList) {
            if(vehicle.getName().equals(vehicleName)) {
                return true;
            }
        }
        //default to return false
        return false;
    }

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
