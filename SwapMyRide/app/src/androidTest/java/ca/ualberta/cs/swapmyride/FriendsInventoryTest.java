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
import ca.ualberta.cs.swapmyride.Model.FriendsList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by carsonmclean on 9/10/15.
 */
public class FriendsInventoryTest extends ActivityInstrumentationTestCase2 {
    public FriendsInventoryTest() {
        super(MainMenu.class);
    }

    // Use Case 13: Search Friends Inventory
    public void testSearchFriendsInventory() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.setName("camclean");
        user.setUserName("Carson Mclean");
        user.setUserEmail("camclean@ualberta.ca");
        user.setUserAddress("300 Shirley Street");

        Vehicle vehicle = new Vehicle();

        //TODO: Add photos back into test
        //vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory(VehicleCategory.COUPE);
        vehicle.setQuality(VehicleQuality.GOOD);
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");

        user.addItem(vehicle);

        //check that a given vehicle exists by name
        assertTrue(user.getInventory().search("Cadillac"));

        //get a list of all vehicles of a category
        assertTrue(user.getInventory().getCategory(VehicleCategory.COUPE).size() == 1);
    }

    // Use Case 14: Make Items Visible
    public void testMakeItemsVisible() {
        FriendsList friendsList = new FriendsList();
        User user = new User();

        user.setName("camclean");
        user.setUserName("Carson Mclean");
        user.setUserEmail("camclean@ualberta.ca");
        user.setUserAddress("300 Shirley Street");

        Vehicle vehicle = new Vehicle();

        //TODO: Add photos back into test
        //vehicle.setPhoto(picture);
        vehicle.setName("Cadillac");
        vehicle.setCategory(VehicleCategory.COUPE);
        vehicle.setQuality(VehicleQuality.GOOD);
        vehicle.setQuantity(1);
        vehicle.setComments("1995 Cadillac");
        vehicle.setPublic(false);

        //add the vehicle
        user.addItem(vehicle);

        assertTrue(user.getInventory().getPublic().size() == 0);

        vehicle.setPublic(true);

        assertTrue(user.getInventory().getPublic().size() == 1);
    }
}
