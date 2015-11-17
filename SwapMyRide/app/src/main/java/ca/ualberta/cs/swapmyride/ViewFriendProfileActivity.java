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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Allows the user to view a profile fo a friend. Selected from a
 * list of friends.
 *
 */

public class ViewFriendProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    DataManager dataManager;
    User myself;
    User friend;
    FriendsList friendsList;
    TextView fullName;
    TextView email;
    TextView address;
    Button removeFriend;
    int position;
    UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfriendprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        uController = new UserController(getApplicationContext());
        dataManager = new DataManager(getApplicationContext());

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        removeFriend = (Button) findViewById(R.id.removeFriend);

        position = getIntent().getIntExtra("Position",0);

        myself = uController.getCurrentUser();

        friendsList = myself.getFriends();

        friend = dataManager.loadUser(friendsList.getFriendList().get(position));

        getSupportActionBar().setTitle(friend.getUserName());

        fullName.setText(friend.getName());
        email.setText(friend.getUserEmail());
        address.setText(friend.getUserAddress());

        removeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });

    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewFriendProfileActivity.this);
        builder.setMessage("Are you SURE you want to remove this friend? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                uController.deleteFriend(friend.getUserName());
                uController.saveCurrentUser();
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void changeUser(String username){
        dataManager = new DataManager(getApplicationContext());
        friend = dataManager.loadUser(username);
    }

}
