package ca.ualberta.cs.swapmyride;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * This module will test for UI functionality of the login screen
 */

public class LoginActivityTest extends ActivityInstrumentationTestCase2 {

    private Button signIn;
    private TextView signUpButton;
    private EditText usernameField;
    private EditText signUpField;
    private Button signUpConfirm;

    public LoginActivityTest(){
        super(ca.ualberta.cs.swapmyride.LoginActivity.class);
    }

    /**
     * testLogin tests functionality for logging in
     *  and by extension signing up for use of the app.
     *  Start an activity monitor on the main menu
     *  click the sign in button, Verify main menu did not start.
     *  enter text into the username box -- this text is not a valid username,
     *  assert that the main menu didn't start.
     *  Click the sign-in button, Check that it moves to the SignUpActivity.
     *  Sign up with user 'Bob', Save and return to screen.
     *  enter 'bob' into the username box
     *  Assert login worked and we are at main menu.
     *  delete user 'bob' for trash cleanup
     */

    public void testLogin(){
        //get the activities
        LoginActivity activity = (LoginActivity) getActivity();

        //Start an activity monitor on the main menu
        //https://developer.android.com/training/activity-testing/activity-functional-testing.html 2015-11-16
        Instrumentation.ActivityMonitor mainMenuActivityMonitor =
                getInstrumentation().addMonitor(MainMenu.class.getName(), null, false);
        MainMenu mainMenuActivity = (MainMenu) mainMenuActivityMonitor.waitForActivityWithTimeout(100);

        //click the sign in button
        signIn = activity.getSignIn();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                signIn.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //Verify main menu did not start
        assertNull("Main Menu is not Null", mainMenuActivity);
        assertEquals("Monitor for ReceiverActivity has been called",
                0, mainMenuActivityMonitor.getHits());

        //enter text into the username box -- this text is not a valid username
        usernameField = activity.getUsernameField();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                usernameField.setText("bobbil");
                signIn.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();

        //assert that the main menu didn't start
        assertNull("Main Menu is not Null", mainMenuActivity);
        assertEquals("Monitor for ReceiverActivity has been called",
                0, mainMenuActivityMonitor.getHits());

        //Click the sign-in button
        signUpButton = activity.getSignUp();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                signUpButton.performClick();
            }
        });

        //create monitor for SignUpActivity
        // Check that it moves to the SignUpActivity
        Instrumentation.ActivityMonitor signUpActivityMonitor =
                getInstrumentation().addMonitor(SignUpActivity.class.getName(), null, false);
        SignUpActivity signUpActivity = (SignUpActivity) signUpActivityMonitor.waitForActivityWithTimeout(10000);
        assertNotNull("SignUp is null", signUpActivity);
        assertEquals("Monitor for SignUpActivity has not been called",
                1, signUpActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                SignUpActivity.class, signUpActivity.getClass());

        //Sign up with user 'Bob'
        signUpField = signUpActivity.getUsername();
        signUpConfirm = signUpActivity.getSave();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                signUpField.setText("bob");
                signUpConfirm.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        signUpActivity.finish();

        //enter 'bob' into the username box
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                usernameField.setText("bob");
                signIn.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        MainMenu mma = (MainMenu) mainMenuActivityMonitor.waitForActivityWithTimeout(100);

        //Assert login worked and we are at main menu.
        assertNotNull("Main Menu is null", mma);
        assertEquals("Activity is of wrong type",
                MainMenu.class, mma.getClass());

        //delete user 'bob' for trash cleanup
        DataManager dm = new DataManager(mma.getApplicationContext());
        dm.deleteUser("bob");

        //remove the monitors
        getInstrumentation().removeMonitor(mainMenuActivityMonitor);
        getInstrumentation().removeMonitor(signUpActivityMonitor);

        //finish the last remaining activity -- end on the main menu.
        activity.finish();

    }

}
