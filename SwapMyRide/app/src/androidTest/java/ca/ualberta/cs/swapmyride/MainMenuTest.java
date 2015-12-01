package ca.ualberta.cs.swapmyride;

import android.app.Instrumentation;
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
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.View.AddFriendProfileActivity;
import ca.ualberta.cs.swapmyride.View.AddInventoryActivity;
import ca.ualberta.cs.swapmyride.View.EditProfileActivity;
import ca.ualberta.cs.swapmyride.View.MainMenu;
import ca.ualberta.cs.swapmyride.View.SearchFriendsActivity;
import ca.ualberta.cs.swapmyride.View.SlidingTabLayout;
import ca.ualberta.cs.swapmyride.View.Tab2;
import ca.ualberta.cs.swapmyride.View.ViewFeedInventoryActivity;
import ca.ualberta.cs.swapmyride.View.ViewFriendProfileActivity;
import ca.ualberta.cs.swapmyride.View.ViewFriendsActivity;
import ca.ualberta.cs.swapmyride.View.ViewVehicleActivity;

/**
 * Created by adrianomarini on 2015-11-18.
 * <p/>
 * In this test, we will ensure that the main menu functions
 * correctly and that all of the intended functions are present
 * and operational
 */
public class MainMenuTest extends ActivityInstrumentationTestCase2 {

    ListView feed;
    ListView inventory;

    public MainMenuTest() {
        super(MainMenu.class);
    }

    /**
     * This test tests the UI functionality of clicking on an item in the
     * friends' inventory feed to verify that the proper view activity
     * opens and that the information that is opened is correct
     */

    public void testClickItem() {
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

    public void testClickInventory() {
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

    public void testViewFriends() {
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

    public void testEditProfile() {
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


    public void testAddFriends() {
        populateTestData();
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
        assertNotNull(searchFriendsActivity);
        EditText searchField = searchFriendsActivity.getSearchField();
        searchField.setText("alicemartin", TextView.BufferType.EDITABLE);
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
        assertEquals(name.getText(), "Alice Martin");
        cleanUp();
    }

    public void testAddItem() {
        populateTestData();
        MainMenu mainMenu = (MainMenu) getActivity();
        Tab2 tab2 = mainMenu.getTab2();
        final Button add = tab2.getAddInventory();
        SlidingTabLayout tabs = mainMenu.getTabs();
        tabs.getChildAt(0);
        mainMenu.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                add.performClick();
            }
        });
        Instrumentation.ActivityMonitor addItemActivityMonitor = getInstrumentation().addMonitor
                (AddInventoryActivity.class.getName(), null, false);
        AddInventoryActivity addInventoryActivity = (AddInventoryActivity)
                addItemActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull(addInventoryActivity);
        EditText name = addInventoryActivity.getVehicleName();
        name.setText("Test Car");
        final Button save = addInventoryActivity.getSaveButton();
        addInventoryActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                save.performClick();
            }
        });
        cleanUp();
    }


    /**
     * This method populates known data into the current user area
     * to ensure that it can be tested and verified.
     */

    public void populateTestData() {
        LocalDataManager ldm = new LocalDataManager(getInstrumentation().getContext());
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setUserName("janesmith");
        user2.setUserName("bobjones");
        user1.setName("Jane Smith");
        user2.setName("Bob Jones");
        user3.setUserName("alicemartin");
        user3.setName("Alice Martin");

        UserSingleton.addCurrentUser(user1);
        user1.addFriend("bobjones");

        Vehicle v1 = new Vehicle();
        v1.setName("Jeep");
        v1.setBelongsTo("janesmith");
        v1.setCategory(VehicleCategory.SUV);
        v1.setPublic(true);
        v1.setQuantity(1);
        v1.setQuality(VehicleQuality.GOOD);

        Vehicle v2 = new Vehicle();
        v2.setName("Honda");
        v2.setBelongsTo("bobjones");
        v1.setCategory(VehicleCategory.SUV);
        v1.setPublic(true);
        v1.setQuantity(1);
        v1.setQuality(VehicleQuality.GOOD);

        Vehicle v3 = new Vehicle();
        v3.setName("Toyota");
        v3.setBelongsTo("alicemartin");
        v1.setCategory(VehicleCategory.SUV);
        v1.setPublic(true);
        v1.setQuantity(1);
        v1.setQuality(VehicleQuality.GOOD);

        ldm.saveUser(user1);
        ldm.saveUser(user2);
        ldm.saveUser(user3);

    }

    /**
     * This method removes the test data that exists from the system
     * to ensure that it will not cause other issues by sticking around
     */
    public void cleanUp() {
        LocalDataManager ldm = new LocalDataManager(getInstrumentation().getContext());
        ldm.deleteUser("janesmith");
        ldm.deleteUser("bobjones");
        ldm.deleteUser("alicemartin");
    }


}
