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

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriendProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView fullName;
    TextView email;
    Button addFriend;
    String username;
    DataManager dataManager;
    User possibleFriend;
    UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriendprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        uController = new UserController(getApplicationContext());
        dataManager = new DataManager(getApplicationContext());

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        addFriend = (Button) findViewById(R.id.addFriend);

        username = getIntent().getStringExtra("Username");

        possibleFriend = dataManager.loadUser(username);

        getSupportActionBar().setTitle(username);
        fullName.setText(possibleFriend.getName());
        email.setText(possibleFriend.getUserEmail());

        // TODO Notify friend of friend request!
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                uController.addFriend(username);
                uController.saveCurrentUser();
                Toast.makeText(getApplicationContext(), username+" Added!",Toast.LENGTH_LONG).show();
                finish();
                */
                // Check that user is not adding themselves
                if (username.equals(UserSingleton.getCurrentUser().getUserName())) {
                    Toast.makeText(getApplicationContext(), "Can't add yourself", Toast.LENGTH_SHORT).show();
                }
                // Check that user is not adding friend twice
                else if (UserSingleton.getCurrentUser().getFriends().hasUser(possibleFriend)) {
                    Toast.makeText(getApplicationContext(), possibleFriend.getUserName() + " has already been added", Toast.LENGTH_SHORT).show();
                }
                else {
                    // Save new friend to current user's friends list
                    UserSingleton.getCurrentUser().addFriend(possibleFriend.getUserName());
                    dataManager.saveUser(UserSingleton.getCurrentUser());

                    // Add current user to new friend's new friends notifications
                    User newFriend = dataManager.loadUser(possibleFriend.getUserName());
                    newFriend.getNotificationManager().notifyFriendRequest(UserSingleton.getCurrentUser().getUserName());
                    dataManager.saveUser(newFriend);
                    Toast.makeText(getApplicationContext(), username + " added!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }
}
