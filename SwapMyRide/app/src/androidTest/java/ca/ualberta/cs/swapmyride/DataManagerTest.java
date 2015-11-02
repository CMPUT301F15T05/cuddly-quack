package ca.ualberta.cs.swapmyride;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * Created by Garry on 2015-11-02.
 */
public class DataManagerTest extends ActivityInstrumentationTestCase2 {
    public DataManagerTest(){super(MainMenu.class);}

    public void testSaveUser(){
        DataManager dataManager = new DataManager(getActivity());
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());
        UserSingleton.addUser(user);
        dataManager.saveUser(user);

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.getName().equals(user.getName()));
        assertTrue(loadTo.getUserEmail().equals(user.getUserEmail()));
        assertTrue(loadTo.getUserAddress().equals(user.getUserAddress()));
        assertTrue(loadTo.getUserName().equals(user.getUserName()));
    }

    public void testDeleteUser(){
        DataManager dm = new DataManager(getActivity());
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
        DataManager dataManager = new DataManager(getActivity());

        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");
        UserSingleton.addUser(user);
        Log.i("FilePath", getActivity().getBaseContext().getFileStreamPath(user.getUserName()).toString());
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        dataManager.saveUser(user);

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

        user.addItem(car);

        dataManager.saveUser(user);

        loadTo = dataManager.loadUser("gbullock");

        //check that the list exists
        assertTrue(loadTo.getInventory().size() == 1);
        //check the given car is the same as the car we gave it
        assertTrue(loadTo.getInventory().getList().get(0).equals(car));
    }
}
