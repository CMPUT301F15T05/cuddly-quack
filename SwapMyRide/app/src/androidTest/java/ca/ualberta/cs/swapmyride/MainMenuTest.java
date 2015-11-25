package ca.ualberta.cs.swapmyride;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Point;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewFinder;
import android.support.test.espresso.action.ViewActions;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.AddInventoryActivity;
import ca.ualberta.cs.swapmyride.View.MainMenu;
import ca.ualberta.cs.swapmyride.View.ViewFeedInventoryActivity;
import ca.ualberta.cs.swapmyride.View.ViewVehicleActivity;

/**
 * Created by adrianomarini on 2015-11-18.
 *
 * In this test, we will ensure that the main menu functions
 * correctly and that all of the intended functions are present
 * and operational
 *
 *
 */
public class MainMenuTest extends ActivityInstrumentationTestCase2{

    ListView feed;
    ListView inventory;

    public MainMenuTest() { super(MainMenu.class); }

    /**
     * This test tests the UI functionality of clicking on an item in the
     * friends' inventory feed to verify that the proper view activity
     * opens and that the information that is opened is correct
     */

    public void testClickItem(){
        populateTestData();
        MainMenu mainMenu = (MainMenu) getActivity();
        //Feed of items should be active
        //Tap on an item in the feed,
        feed = mainMenu.getFeed();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                feed.performItemClick(feed, 0, 1);
            }
        });
        getInstrumentation().waitForIdleSync();
        //ViewVehicleActivity should be active
        Instrumentation.ActivityMonitor viewItemActivityMonitor =
                getInstrumentation().addMonitor(ViewFeedInventoryActivity.class.getName(), null, false);
        ViewFeedInventoryActivity viewFeedInventoryActivity = (ViewFeedInventoryActivity)
                viewItemActivityMonitor.waitForActivityWithTimeout(100);
        assertNotNull(viewFeedInventoryActivity);
        //Assert information matches the prescribed info
        TextView name = viewFeedInventoryActivity.getTheName();
        assertEquals(name.getText(), "Toyota");
        cleanUp();
    }

    public void testClickInventory(){
        populateTestData();
        MainMenu mainMenu = (MainMenu) getActivity();
        //Swipe left to get to inventory screen
        ViewActions.swipeLeft();
        //tap on an item
        inventory = mainMenu.getInventoryView();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                inventory.performItemClick(inventory, 0, 1);
            }
        });
        getInstrumentation().waitForIdleSync();
        //Assert the view activity is active
        Instrumentation.ActivityMonitor viewInventoryActivityMonitor =
                getInstrumentation().addMonitor(ViewVehicleActivity.class.getName(), null, false);
        ViewVehicleActivity viewVehicleActivity = (ViewVehicleActivity)
                    viewInventoryActivityMonitor.waitForActivityWithTimeout(100);
        assertNotNull(viewVehicleActivity);
        //Assert information matches
        TextView name = viewVehicleActivity.getTheName();
        assertEquals(name.getText(), "Jeep");
        //Tap Edit
        final Button edit = viewVehicleActivity.getEditButton();
        viewVehicleActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edit.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Instrumentation.ActivityMonitor editInventoryActivityMonitor =
                getInstrumentation().addMonitor(AddInventoryActivity.class.getName(), null, false);
        AddInventoryActivity addInventoryActivity = (AddInventoryActivity)
                editInventoryActivityMonitor.waitForActivityWithTimeout(100);
        assertNotNull(addInventoryActivity);

        //Change name to "Meepy"
        EditText name1 = addInventoryActivity.getVehicleName();
        name1.setText("Meepy");
        final Button save = addInventoryActivity.getSaveButton();
        addInventoryActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                save.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //assert information matches changes
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                inventory.performItemClick(inventory, 0, 1);
            }
        });
        getInstrumentation().waitForIdleSync();
        TextView name3 = viewVehicleActivity.getTheName();
        assertEquals("Meepy", name3.getText());
        cleanUp();
    }

    public void testViewFriends(){
        populateTestData();
        //Tab3 should be active, tap View Friends

        //ViewFriendsActivity should be active

        //Verify a friend exists

        //Tap that friend -- ViewFriendProfileActivity should be active

        //Assert matching information

        cleanUp();
    }

    public void testEditProfile(){
        populateTestData();
        //Tab3 should be active, tap Edit Profile

        //Verify information is active

        //change name + click save

        //re-enter -- verify information matches
        cleanUp();
    }

    public void testAddFriends(){
        populateTestData();
        //Click search button

        //Verify search activity starts

        //search for string

        //tap first result and verify information matches

        //tap the add button

        //verify activity returned

        cleanUp();
    }

    public void testAddItem(){
        populateTestData();

        cleanUp();
    }

    public void testSearch(){
        populateTestData();

        cleanUp();
    }

    /**
     * This method populates known data into hte current user area
     * to ensure that it can be tested and verified.
     */

    public void populateTestData(){
        Context context = getActivity();
        DataManager dm = new DataManager(context);
        //Create 2 users and make them friends
        User main = new User();
        User friend = new User();

        main.setUserName("bob");
        main.setName("Bob");
        main.setUserAddress("123 Fake Street");
        main.setUserEmail("bob@bob.bob");

        friend.setUserName("jane");
        friend.setName("Jane");
        friend.setUserAddress("234 Fake Street");
        friend.setUserEmail("jane@jane.jane");

        main.addFriend("jane");

        //Give them 2 inventory items each
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();
        Vehicle v3 = new Vehicle();
        Vehicle v4 = new Vehicle();

        v1.setName("Jeep");
        v1.setCategory(VehicleCategory.SUV);
        v1.setQuantity(1);
        v1.setQuality(VehicleQuality.SHOWROOM);

        v2.setName("Honda");
        v2.setCategory(VehicleCategory.SEDAN);
        v2.setQuality(VehicleQuality.RUSTBUCKET);
        v2.setQuantity(1);

        main.addItem(v1);
        main.addItem(v2);

        v3.setName("Toyota");
        v3.setCategory(VehicleCategory.SUV);
        v3.setQuantity(1);
        v3.setQuality(VehicleQuality.SHOWROOM);

        v4.setName("Mitsubishi");
        v4.setCategory(VehicleCategory.SEDAN);
        v4.setQuality(VehicleQuality.RUSTBUCKET);
        v4.setQuantity(1);

        friend.addItem(v3);
        friend.addItem(v4);

        dm.saveUser(main);
        dm.saveUser(friend);
    }

    /**
     * This method removes the test data that exists from the system
     * to ensure that it will not cause other issues by sticking around
     */
    public void cleanUp(){
        Context context = getInstrumentation().getContext();
        DataManager dm = new DataManager(context);
        //delete the two created users
        dm.deleteUser("bob");
        dm.deleteUser("jane");
    }

}
