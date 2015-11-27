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

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Garry on 2015-11-02.
 */
public class DataManagerTest extends ActivityInstrumentationTestCase2 {

    public DataManagerTest(){super(MainMenu.class);}

    public void testSaveUser(){
        LocalDataManager dataManager = new LocalDataManager(getActivity());
        UserController uController = new UserController(getActivity());
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());
        uController.addCurrentUser(user);
        dataManager.saveUser(user);

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.getName().equals(user.getName()));
        assertTrue(loadTo.getUserEmail().equals(user.getUserEmail()));
        assertTrue(loadTo.getUserAddress().equals(user.getUserAddress()));
        assertTrue(loadTo.getUserName().equals(user.getUserName()));

        dataManager.deleteUser("gbullock");
    }

    public void testDeleteUser(){
        Log.i("WERE Here","RIGHT HERE");
        LocalDataManager dm = new LocalDataManager(getActivity());
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");

        dm.saveUser(user);

        //check that the file exists
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        dm.deleteUser("gbullock");

        //check that the file no longer exists
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());
    }

    public void testSaveUserWithVehicle(){
        LocalDataManager dataManager = new LocalDataManager(getActivity());

        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());

        //ensure the file does not previously exist
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        dataManager.saveUser(user);

        //check the file exists
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.getName().equals(user.getName()));
        assertTrue(loadTo.getUserEmail().equals(user.getUserEmail()));
        assertTrue(loadTo.getUserAddress().equals(user.getUserAddress()));
        assertTrue(loadTo.getUserName().equals(user.getUserName()));

        Vehicle car = new Vehicle();
        car.setName("2008 Cadillac");
        car.setCategory(VehicleCategory.SEDAN);
        car.setQuality(VehicleQuality.OKAY);
        car.setQuantity(1);
        car.setPublic(true);
        car.setComments("These are some comments yep yep");
        user.addItem(car);

        dataManager.saveUser(user);

        loadTo = dataManager.loadUser("gbullock");

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

        dataManager.deleteUser("gbullock");

        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

    }

    public void testSearchUser(){
        LocalDataManager dataManager = new LocalDataManager(getActivity());

        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());

        dataManager.deleteUser(user.getUserName());
        //ensure the file does not previously exist
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        //assert that it does not find any users
        assertFalse(dataManager.searchUser(user.getUserName()));

        dataManager.saveUser(user);

        //ensure the file now exists
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        assertTrue(dataManager.searchUser(user.getUserName()));

        dataManager.deleteUser(user.getUserName());

        //ensure the file no longer exists
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());;
    }
}
