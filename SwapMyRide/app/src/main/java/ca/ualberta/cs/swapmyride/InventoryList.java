
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

    public int size(){
        return vehicleList.size();
    }




}
