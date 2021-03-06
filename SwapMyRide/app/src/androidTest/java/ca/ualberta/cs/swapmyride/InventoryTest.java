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

import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Garry on 2015-10-08.
 */
public class InventoryTest extends ActivityInstrumentationTestCase2 {
    public InventoryTest() {
        super(MainMenu.class);
    }

    // Use Case 1: Add Vehicle
    public void testAddItem() {
        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        //list should be empty
        assertTrue(inventoryList.getList().isEmpty());
        inventoryList.add(vehicle);
        //make sure an vehicle was added
        assertTrue(inventoryList.size() == 1);
        //make sure it is the correct vehicle
        assertTrue(inventoryList.getList().get(0) == vehicle);
    }

    // Use Case 2: Browse Inventory
    public void testBrowseInventory() {
        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        //list should be empty
        assertTrue(inventoryList.getList().size() == 0);
        inventoryList.add(vehicle);
        //check there is an vehicle in the list
        assertFalse(inventoryList.getList().size() == 0);
        //check the vehicle is the vehicle we put into the list
        assertTrue(inventoryList.getList().get(0) == vehicle);
    }

    // Use Case 3: View Vehicle
    public void testViewItem() {
        Vehicle vehicle = new Vehicle();
        //vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory(VehicleCategory.COUPE);
        vehicle.setQuality(VehicleQuality.GOOD);
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");

        //assertTrue(vehicle.getPhoto().equals(photo));
        assertTrue(vehicle.getName().equals("Cadillac"));
        assertTrue(vehicle.getCategory().equals(VehicleCategory.COUPE));
        assertTrue(vehicle.getQuality().equals(VehicleQuality.GOOD));
        assertTrue(vehicle.getQuantity().equals(1));
        assertTrue(vehicle.getComments().equals("1995 Cadillac"));
    }

    // Use Case 4: Set Public
    public void testSetPublic() {
        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        vehicle.setPublic(false);
        inventoryList.add(vehicle);
        //check the vehicle does not appear in the public return
        assertTrue(inventoryList.getPublic().size() == 0);
        //check the vehicle does appear in the private list
        assertTrue(inventoryList.getPrivate().size() == 1);
    }

    // Use Case 5: Modify Items
    public void testModifyItems() {
        Vehicle vehicle = new Vehicle();
        vehicle.setName("Vehicle");
        assertTrue(vehicle.getName().equals("Vehicle"));
        vehicle.setName("Car");
        assertTrue(vehicle.getName().equals("Car"));
    }

    // Use Case 6: Delete Vehicle
    public void testDeleteItem() {
        InventoryList inventoryList = new InventoryList();
        Vehicle vehicle = new Vehicle();
        Vehicle newVehicle = new Vehicle();
        //list should be empty
        assertTrue(inventoryList.getList().size() == 0);
        inventoryList.add(vehicle);
        inventoryList.add(newVehicle);
        //check there is an vehicle in the list
        assertFalse(inventoryList.getList().size() == 0);
        inventoryList.delete(newVehicle);
        //check the size is correct
        assertTrue(inventoryList.getList().size() == 1);
        //check the vehicle left is the correct vehicle
        assertTrue(inventoryList.getList().contains(vehicle));
    }

    // Use Case 7: Minimum Navigation
    // No related test case as evaluation is not concrete

}
