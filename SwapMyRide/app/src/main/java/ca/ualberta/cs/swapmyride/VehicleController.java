package ca.ualberta.cs.swapmyride;

import android.graphics.drawable.Drawable;

/**
 * Created by Garry on 2015-11-01.
 */
public class VehicleController {

    //From UML: getVehicle returns a ViewVehicleActivity with the vehicle that is tapped
    //on in a listview
    /*
    public Vehicle getVehicle(){
        //Return a specific vehicle to be displayed by a view?
        return new Vehicle();
    }
    */

    public void addVehicle(Vehicle vehicle){
        UserSingleton.getCurrentUser().addItem(vehicle);
    }

    //I believe this method should be broken up into several methods that update specific attributes
    //of a vehicle... ie editVehicleName, editVehicleQuantity. It should also take two arguments,
    //the vehicle to edit, and what should be changed.
    public void editVehicle(Vehicle vehicle){

    }

    public void addPhoto(Drawable photo){
        
    }
}
