package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by Garry on 2015-11-27.
 */
public class LocalDataManagerTest extends ActivityInstrumentationTestCase2 {

    public LocalDataManagerTest() {
        super(MainMenu.class);
    }

    public void testSaveUser() {
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

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User loadTo = dataManager.loadUser("gbullock");

        assertTrue(loadTo.getName().equals(user.getName()));
        assertTrue(loadTo.getUserEmail().equals(user.getUserEmail()));
        assertTrue(loadTo.getUserAddress().equals(user.getUserAddress()));
        assertTrue(loadTo.getUserName().equals(user.getUserName()));

        dataManager.deleteUser("gbullock");
    }

    public void testDeleteUser() {
        Log.i("WERE Here", "RIGHT HERE");
        LocalDataManager dm = new LocalDataManager(getActivity());
        User user = new User();
        user.setName("Garry");
        user.setUserAddress("4465");
        user.setUserName("gbullock");
        user.setUserEmail("gbullock@ualbert.ca");

        dm.saveUser(user);

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //check that the file exists
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        dm.deleteUser("gbullock");

        //check that the file no longer exists
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());
    }

    public void testSaveUserWithVehicle() {
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

        dataManager.saveUser(user);

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    public void testSearchUser() {
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

        try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ensure the file now exists
        assertTrue(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());

        assertTrue(dataManager.searchUser(user.getUserName()));

        dataManager.deleteUser(user.getUserName());

        //ensure the file no longer exists
        assertFalse(getActivity().getBaseContext().getFileStreamPath(user.getUserName()).exists());
    }

    public void testSavePhoto() {
        LocalDataManager dataManager = new LocalDataManager(getActivity());
        Photo photo = new Photo(getActivity());
        Log.i("PhotoTest", "PhotoID - " + photo.getUid().getID());

        dataManager.savePhoto(photo);

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //assertTrue(dataManager.searchPhoto(photo.getUid().getID()));
        assertTrue(getActivity().getBaseContext().getFileStreamPath(photo.getUid().getID()).exists());

        dataManager.deletePhoto(photo.getUid().getID());

        assertFalse(getActivity().getBaseContext().getFileStreamPath(photo.getUid().getID()).exists());
    }

    public void testLoadPhoto() {
        LocalDataManager dataManager = new LocalDataManager(getActivity());
        Photo photo = new Photo(getActivity());
        Log.i("PhotoTest", "PhotoID - " + photo.getUid().getID());

        dataManager.savePhoto(photo);

        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertTrue(getActivity().getBaseContext().getFileStreamPath(photo.getUid().getID()).exists());

        Photo photo2 = dataManager.loadPhoto(photo.getUid().getID());

        Log.i("TestPhoto", "First Photo ID:" + photo.getUid().getID() + " Photo 2 ID: " + photo2.getUid().getID());
        assertTrue(photo.equals(photo2));

        dataManager.deletePhoto(photo.getUid().getID());

        assertFalse(getActivity().getBaseContext().getFileStreamPath(photo.getUid().getID()).exists());
    }
}
