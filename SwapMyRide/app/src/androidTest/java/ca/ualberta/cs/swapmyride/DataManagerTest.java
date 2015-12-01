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
import android.util.Log;

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.NetworkDataManager;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.LoginActivity;

/**
 * Created by Garry on 2015-11-02.
 */
public class DataManagerTest extends ActivityInstrumentationTestCase2 {

    public DataManagerTest() {
        super(LoginActivity.class);
    }

    public void testSaveUser() {
        NetworkDataManager ndm = new NetworkDataManager();
        DataManager dm = new DataManager(getActivity());
        LocalDataManager ldm = new LocalDataManager(getActivity());


        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        user.setDownloadImages(true);

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(dm.searchUserLocal(user.getUserName()));
        assertFalse(dm.searchUserServer(user.getUserName()));

        //This is what we are testing!
        dm.saveUser(user);

        //wait a decent amount of time to ensure the save has time to happen
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(dm.searchUserLocal(user.getUserName()));
        assertTrue(dm.searchUserServer(user.getUserName()));

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());
    }

    public void testDeleteUser() {
        NetworkDataManager ndm = new NetworkDataManager();
        DataManager dm = new DataManager(getActivity());
        LocalDataManager ldm = new LocalDataManager(getActivity());


        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        user.setDownloadImages(true);

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());

        try {
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(dm.searchUserLocal(user.getUserName()));
        assertFalse(dm.searchUserServer(user.getUserName()));

        dm.saveUser(user);

        //wait a decent amount of time to ensure the save has time to happen
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(dm.searchUserLocal(user.getUserName()));
        assertTrue(dm.searchUserServer(user.getUserName()));

        dm.deleteUser(user.getUserName());

        //wait a decent amount of time to ensure the delete has time to happen
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(dm.searchUserLocal(user.getUserName()));
        assertFalse(dm.searchUserServer(user.getUserName()));
    }

    public void testGetUser() {
        NetworkDataManager ndm = new NetworkDataManager();
        DataManager dm = new DataManager(getActivity());
        LocalDataManager ldm = new LocalDataManager(getActivity());

        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        user.setDownloadImages(true);

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());

        //save the user strictly to the server first, and make sure that it
        ndm.saveUser(user);

        try {
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(dm.searchUserServer(user.getUserName()));

        User user2 = dm.loadUser(user.getUserName());

        assertTrue(user.equals(user2));

        dm.deleteUser(user.getUserName());

        try {
            Thread.sleep(250);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertFalse(dm.searchUserServer(user.getUserName()));

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());

        Log.i("NEWDATAMANAGER", "Saving user locally!");
        //test saving and loading locally.
        ldm.saveUser(user);

        assertTrue(dm.searchUserLocal(user.getUserName()));
        assertFalse(dm.searchUserServer(user.getUserName()));

        user2 = dm.loadUser(user.getUserName());

        assertTrue(user.equals(user2));

        dm.deleteUser(user.getUserName());
    }

    public void testSaveUserWithVehicle() {
        NetworkDataManager ndm = new NetworkDataManager();
        DataManager dm = new DataManager(getActivity());
        LocalDataManager ldm = new LocalDataManager(getActivity());


        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        user.setDownloadImages(true);

        ndm.deleteUser(user.getUserName());
        ldm.deleteUser(user.getUserName());

        dm.saveUser(user);

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(dm.searchUserLocal(user.getUserName()));
        assertTrue(dm.searchUserServer(user.getUserName()));

        Vehicle car = new Vehicle();
        car.setName("2008 Cadillac");
        car.setCategory(VehicleCategory.SEDAN);
        car.setQuality(VehicleQuality.OKAY);
        car.setQuantity(1);
        car.setPublic(true);
        car.setComments("These are some comments yep yep");
        user.addItem(car);

        dm.saveUser(user);

        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(dm.searchUserLocal(user.getUserName()));
        assertTrue(dm.searchUserServer(user.getUserName()));

        User loadTo = dm.loadUser(user.getUserName());

        //check that the list exists
        assertTrue(loadTo.getInventory().size() == 1);
        //assert no friends have magically appeared
        assertTrue(loadTo.getFriends().size() == 0);
        //assert no random tradelist appeared
        assertTrue(loadTo.getPastTrades().getSize() == 0);
        assertTrue(loadTo.getPendingTrades().getSize() == 0);
        //check the given car is the same as the car we gave it
        Vehicle newCar = loadTo.getInventory().getList().get(0);

        assertTrue(newCar.getName().equals(car.getName()));
        assertTrue(newCar.getCategory().equals(car.getCategory()));
        assertTrue(newCar.getQuality().equals(car.getQuality()));
        assertTrue(newCar.getQuantity().equals(car.getQuantity()));
        assertTrue(newCar.getPublic() == car.getPublic());
        assertTrue(newCar.getComments().equals(car.getComments()));
    }
}
