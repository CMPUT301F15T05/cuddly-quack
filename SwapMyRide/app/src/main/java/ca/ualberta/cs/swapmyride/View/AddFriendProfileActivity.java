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
package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.NetworkDataManager;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.R;

/**
 * This activity specifically displays another user that the current user
 * of the app is planning to add - or has been notified of the other user's
 * "following" of them.
 */

public class AddFriendProfileActivity extends AppCompatActivity {


    Toolbar toolbar;
    TextView fullName;
    TextView email;
    Button addFriend;
    String username;
    NetworkDataManager dataManager;
    User possibleFriend;
    UserController uController;

    /**
     * the onCreate here displays all information of the possible friend.
     * Initializes all information from the user object returned from
     * the Datamanager and pushes the information into the fields.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriendprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        uController = new UserController(getApplicationContext());
        dataManager = new NetworkDataManager();

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        addFriend = (Button) findViewById(R.id.addFriend);

        username = getIntent().getStringExtra("Username");

        possibleFriend = dataManager.retrieveUser(username);

        getSupportActionBar().setTitle(username);
        fullName.setText(possibleFriend.getName());
        email.setText(possibleFriend.getUserEmail());

        // TODO Notify friend of friend request!

        /**
         * This onClick function serves to control the button used to add a friend
         * In this button click, it is required to pass checks about the user - specifically
         * making sure the User is not adding themselves, and that the user does not add a
         * friend whom they are already following.
         *
         * If the above checks pass, the friend is added to the friends list of the current
         * user of the app. Said section also notifies the user with a Toast if the add succeeds
         */

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check that user is not adding themselves
                if (username.equals(UserSingleton.getCurrentUser().getUserName())) {
                    Toast.makeText(getApplicationContext(), "Can't add yourself", Toast.LENGTH_SHORT).show();
                }
                // Check that user is not adding friend twice
                else if (UserSingleton.getCurrentUser().getFriends().hasUser(possibleFriend.getUserName())) {
                    Toast.makeText(getApplicationContext(), possibleFriend.getUserName() + " has already been added", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Save new friend to current user's friends list
                    UserSingleton.getCurrentUser().addFriend(possibleFriend.getUserName());
                    LocalDataManager ldm = new LocalDataManager(getApplicationContext());
                    ldm.saveUser(UserSingleton.getCurrentUser());
                    // Add current user to new friend's new friends notifications
                    //User newFriend = dataManager.loadUser(possibleFriend.getUserName());
                    possibleFriend.getNotificationManager().notifyFriendRequest(UserSingleton.getCurrentUser().getUserName());
                    ldm.saveUser(possibleFriend);
                    dataManager.saveUser(possibleFriend);
                    Toast.makeText(getApplicationContext(), username + " added!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    public Button getAddFriend(){
        return addFriend;
    }
}
