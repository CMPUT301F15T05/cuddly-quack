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
import android.graphics.drawable.Drawable;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Vehicle;

/**
 *
 * Vehicle controller helps us deal with data regarding vehicles and their use
 *
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

    public void addVehicle(Vehicle vehicle) {
        UserSingleton.getCurrentUser().addItem(vehicle);
    }

    //I believe this method should be broken up into several methods that update specific attributes
    //of a vehicle... ie editVehicleName, editVehicleQuantity. It should also take two arguments,
    //the vehicle to edit, and what should be changed.
    public void editVehicle(Vehicle vehicle) {

    }

    public void addPhoto(Drawable photo) {

    }

    public void deletePhoto(Vehicle vehicle, Context context) {
        vehicle.deletePhotoArrayList(context);
    }
}
