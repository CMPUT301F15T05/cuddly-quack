package ca.ualberta.cs.swapmyride;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Point;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.view.Display;
import android.view.WindowManager;

import java.math.RoundingMode;

import ca.ualberta.cs.swapmyride.View.MainMenu;
import ca.ualberta.cs.swapmyride.View.Tab1;

/**
 * Created by adrianomarini on 2015-11-18.
 *
 * In this test, we will ensure that the main menu functions
 * correctly and that all of the intended functions are present
 * and operational
 *
 */
public class MainMenuTest extends ActivityInstrumentationTestCase2 {

    public MainMenuTest() { super(MainMenu.class); }

    public void testMainMenu(){
        Context context = super.getActivity();

        /**
         * Instructions on how to get display size taken from:
         * http://stackoverflow.com/questions/1016896/get-screen-dimensions-in-pixels
         * User: Josef Pfleger           Date: 2015-11-18
         */

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        int centreWidth = width / 2;
        int heightCentre = height / 2;

        /**
         * Instructions on how to use TouchUtils taken from:
         * http://developer.android.com/reference/android/test/TouchUtils.html
         * Date: 2015-11-18
         */

        //Feed of items should be active

        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);
        //Inventory feed should be active

        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);
        //List of extra things should be active

        TouchUtils.drag(this, 0, centreWidth, heightCentre, heightCentre, 1);
        //Inventory feed should be active

        TouchUtils.drag(this, 0, centreWidth, heightCentre, heightCentre, 1);
        //Item feed should be active

    }

    public void testClickItem(){
        Context context = super.getActivity();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int centreWidth = width / 2;
        int heightCentre = height / 2;

        //Feed of items should be active

        //Tap on an item in the feed,

        //ViewVehicleActivity should be active

        //Assert information matches the prescribed info
    }

    public void testClickInventory(){
        Context context = super.getActivity();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int centreWidth = width / 2;
        int heightCentre = height / 2;
        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);

        //Feed of inventory should be active

        //tap on an item

        //Assert the view activity is active

        //Assert information matches

        //Try changing an item's attribute + save

        //Feed inventory should be active

        // tap same item

        //assert information matches changes


    }

    public void testViewFriends(){
        Context context = super.getActivity();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int centreWidth = width / 2;
        int heightCentre = height / 2;
        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);
        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);

        //Tab3 should be active, tap View Friends

        //ViewFriendsActivity should be active

        //Verify a friend exists

        //Tap that friend -- ViewFriendProfileActivity should be active

        //Assert matching information

    }

    public void testEditProfile(){
        Context context = super.getActivity();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        int centreWidth = width / 2;
        int heightCentre = height / 2;
        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);
        TouchUtils.drag(this, width, centreWidth, heightCentre, heightCentre, 1);

        //Tab3 should be active, tap Edit Profile

        //Verify information is active

        //change name + click save

        //re-enter -- verify information matches
    }

    public void testAddFriends(){
        //Click search button

        //Verify search activity starts

        //search for string

        //tap first result and verify information matches

        //tap the add button

        //verify activity returned

    }

    public void testAddItem(){

    }

    public void testSearch(){

    }
    
}
