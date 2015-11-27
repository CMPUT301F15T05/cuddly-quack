package ca.ualberta.cs.swapmyride;

import android.app.Instrumentation;
import android.content.Context;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.Geolocation;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.AddFriendProfileActivity;
import ca.ualberta.cs.swapmyride.View.AddInventoryActivity;
import ca.ualberta.cs.swapmyride.View.EditProfileActivity;
import ca.ualberta.cs.swapmyride.View.MainMenu;
import ca.ualberta.cs.swapmyride.View.SearchFriendsActivity;
import ca.ualberta.cs.swapmyride.View.SlidingTabLayout;
import ca.ualberta.cs.swapmyride.View.ViewFeedInventoryActivity;
import ca.ualberta.cs.swapmyride.View.ViewFriendProfileActivity;
import ca.ualberta.cs.swapmyride.View.ViewFriendsActivity;
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
                View v = inventory.getChildAt(0);
                if (v != null) {
                    inventory.performItemClick(v, 0, v.getId());
                }
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
        SlidingTabLayout tabs = mainMenu.getTabs();
        tabs.getChildAt(1);
        //tap on an item
        inventory = mainMenu.getInventoryView();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View v = inventory.getChildAt(0);
                inventory.performItemClick(v, 0, 1);
            }
        });
        getInstrumentation().waitForIdleSync();
        //Assert the view activity is active
        Instrumentation.ActivityMonitor viewInventoryActivityMonitor =
                getInstrumentation().addMonitor(ViewVehicleActivity.class.getName(), null, false);
        ViewVehicleActivity viewVehicleActivity = (ViewVehicleActivity)
                    viewInventoryActivityMonitor.waitForActivityWithTimeout(10000);
        //assertNotNull(viewVehicleActivity);
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
                View v = inventory.getChildAt(0);
                inventory.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();
        TextView name3 = viewVehicleActivity.getTheName();
        assertEquals("Meepy", name3.getText());
        cleanUp();
    }

    public void testViewFriends(){
        populateTestData();
        MainMenu mainMenu = (MainMenu) getActivity();
        //Tab3 should be active, tap View Friends
        SlidingTabLayout tabs = mainMenu.getTabs();
        tabs.getChildAt(2);
        final TextView friends = mainMenu.getViewFriends();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                friends.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //ViewFriendsActivity should be active
        Instrumentation.ActivityMonitor friendsListMonitor =
                getInstrumentation().addMonitor(ViewFriendsActivity.class.getName(), null, false);
        ViewFriendsActivity viewFriendsActivity = (ViewFriendsActivity)
                friendsListMonitor.waitForActivityWithTimeout(100);
        //Verify a friend exists
        assertNotNull(viewFriendsActivity.getFriendsList());
        assertNotNull(viewFriendsActivity);
        //Tap that friend -- ViewFriendProfileActivity should be active
        final ListView myFriends = viewFriendsActivity.getFriendsList();
        viewFriendsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View v = myFriends.getChildAt(0);
                myFriends.performItemClick(v, 0, v.getId());
            }
        });
        //Assert matching information
        Instrumentation.ActivityMonitor viewFriendProfileMonitor =
                getInstrumentation().addMonitor(ViewFriendProfileActivity.class.getName(), null, false);
        ViewFriendProfileActivity viewFriendProfileActivity = (ViewFriendProfileActivity)
                viewFriendProfileMonitor.waitForActivityWithTimeout(1000);
        assertNotNull(viewFriendProfileActivity);
        TextView name = viewFriendProfileActivity.getFullName();
        assertEquals(name.getText(), "Jane");

        cleanUp();
    }

    public void testEditProfile(){
        populateTestData();
        MainMenu mainMenu = (MainMenu) getActivity();
        //Tab3 should be active, tap Edit Profile
        SlidingTabLayout tabs = mainMenu.getTabs();
        tabs.getChildAt(2);
        final TextView edit = mainMenu.getEditProfile();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edit.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //Verify information is active
        Instrumentation.ActivityMonitor editProfileActivityMonitor =
                getInstrumentation().addMonitor(EditProfileActivity.class.getName(), null, false);
        EditProfileActivity editProfileActivity = (EditProfileActivity)
                editProfileActivityMonitor.waitForActivityWithTimeout(100);
        //change name + click save
        TextView name = editProfileActivity.getNameText();
        assertEquals("Bob", name.getText());
        name.setText("Joe");
        final Button save = editProfileActivity.getSaveButton();
        editProfileActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                save.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //re-enter -- verify information matches
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edit.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        TextView name1 = editProfileActivity.getNameText();
        assertEquals("Joe", name1.getText());

        cleanUp();
    }


    public void testAddFriends(){
        populateTestData();
        populateThird();
        MainMenu mainMenu = (MainMenu) getActivity();
        //Click search button
        final ActionMenuItemView add = mainMenu.getActionAddFriend();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                add.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //Verify search activity starts
        Instrumentation.ActivityMonitor sAM = getInstrumentation().addMonitor
                (SearchFriendsActivity.class.getName(), null, false);
        SearchFriendsActivity searchFriendsActivity = (SearchFriendsActivity)
                sAM.waitForActivityWithTimeout(1000);
        //search for string
        EditText searchField = searchFriendsActivity.getSearchField();
        searchField.setText("bill");
        final ImageButton search = searchFriendsActivity.getSearch();
        searchFriendsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                search.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        //tap first result and verify information matches
        final ListView friendView = searchFriendsActivity.getFriendView();
        searchFriendsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View v = friendView.getChildAt(0);
                friendView.performItemClick(v, 0, 1);
            }
        });
        getInstrumentation().waitForIdleSync();
        Instrumentation.ActivityMonitor afAM = getInstrumentation().addMonitor
                (AddFriendProfileActivity.class.getName(), null, false);
        AddFriendProfileActivity addFriendProfileActivity = (AddFriendProfileActivity)
                afAM.waitForActivityWithTimeout(1000);
        //tap the add button
        final Button addMe = addFriendProfileActivity.getAddFriend();
        addFriendProfileActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addMe.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        SlidingTabLayout tabs = mainMenu.getTabs();
        tabs.getChildAt(2);
        final TextView seeFriends = mainMenu.getViewFriends();
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                seeFriends.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        Instrumentation.ActivityMonitor vfAM = getInstrumentation().addMonitor
                (ViewFriendsActivity.class.getName(), null, false);
        ViewFriendsActivity viewFriendsActivity = (ViewFriendsActivity)
                vfAM.waitForActivityWithTimeout(1000);
        assertNotNull(viewFriendsActivity);

        final ListView friends = viewFriendsActivity.getFriendsList();
        assertNotNull(friends);
        viewFriendsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View v = friends.getChildAt(1);
                friends.performItemClick(v, 1, 1);
            }
        });

        Instrumentation.ActivityMonitor vfpAM = getInstrumentation().addMonitor
                (ViewFriendProfileActivity.class.getName(), null, false);
        ViewFriendProfileActivity viewFriendProfileActivity = (ViewFriendProfileActivity)
                vfpAM.waitForActivityWithTimeout(1000);
        TextView name = viewFriendProfileActivity.getFullName();
        assertEquals(name, "Bill");
        cleanUp();
        cleanThird();
    }

    public void testAddItem(){
        populateTestData();

        cleanUp();
    }

    /**
     * This method populates known data into hte current user area
     * to ensure that it can be tested and verified.
     */

    public void populateTestData(){
        Context context = getActivity();
        LocalDataManager dm = new LocalDataManager(context);
        Geolocation geolocation = new Geolocation();
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
        v1.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));

        v2.setName("Honda");
        v2.setCategory(VehicleCategory.SEDAN);
        v2.setQuality(VehicleQuality.RUSTBUCKET);
        v2.setQuantity(1);
        v2.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));

        main.addItem(v1);
        main.addItem(v2);

        v3.setName("Toyota");
        v3.setCategory(VehicleCategory.SUV);
        v3.setQuantity(1);
        v3.setQuality(VehicleQuality.SHOWROOM);
        v3.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));

        v4.setName("Mitsubishi");
        v4.setCategory(VehicleCategory.SEDAN);
        v4.setQuality(VehicleQuality.RUSTBUCKET);
        v4.setQuantity(1);
        v4.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));

        friend.addItem(v3);
        friend.addItem(v4);

        dm.saveUser(main);
        dm.saveUser(friend);

        UserSingleton.addCurrentUser(main);
    }

    /**
     * This method removes the test data that exists from the system
     * to ensure that it will not cause other issues by sticking around
     */
    public void cleanUp(){
        Context context = getInstrumentation().getContext();
        LocalDataManager dm = new LocalDataManager(context);
        //delete the two created users
        dm.deleteUser("bob");
        dm.deleteUser("jane");
    }

    public void cleanThird(){
        Context context = getInstrumentation().getContext();
        LocalDataManager dm = new LocalDataManager(context);
        //delete the created user
        dm.deleteUser("bill");
    }


    public void populateThird(){
        Context context = getActivity();
        LocalDataManager dm = new LocalDataManager(context);
        Geolocation geolocation = new Geolocation();
        //Create user
        User three = new User();

        three.setUserName("bill");
        three.setName("Bill");
        three.setUserAddress("345 Fake Street");
        three.setUserEmail("bill@bill.bill");

        //Give them 2 inventory items
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();

        v1.setName("Tank");
        v1.setCategory(VehicleCategory.SUV);
        v1.setQuantity(1);
        v1.setQuality(VehicleQuality.SHOWROOM);
        v1.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));

        v2.setName("Chrysler");
        v2.setCategory(VehicleCategory.SEDAN);
        v2.setQuality(VehicleQuality.RUSTBUCKET);
        v2.setQuantity(1);
        v2.setLocation(geolocation.getCurrentLocation(getActivity().getApplicationContext(), getActivity()));
    }

}