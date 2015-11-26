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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.FriendAdapter;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.R;
import ca.ualberta.cs.swapmyride.Controller.UserController;

/**
 * Allows user to view list of friends
 *
 * Gets user list and determines list of friends
 * Displays list of friends.
 *
 * On an item click -- displays the user profile.
 *
 */

public class ViewFriendsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView friendView;
    FriendAdapter adapter;
    ArrayList<User> friendsList;
    UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfriends);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        uController = new UserController(getApplicationContext());

        friendView = (ListView) findViewById(R.id.friendView);

        uController.updateFriends();
        friendsList = uController.getFriends();

        adapter = new FriendAdapter(getApplicationContext(), friendsList);

        friendView.setAdapter(adapter);

        friendView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ViewFriendProfileActivity.class);

                //add the vehicle that has been selected to the intent to pass
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        uController.updateFriends();
        friendsList = uController.getFriends();
        adapter = new FriendAdapter(getApplicationContext(), friendsList);
        friendView.setAdapter(adapter);
    }

    public ListView getFriendsList(){
        return (ListView) findViewById(R.id.friendView);
    }
}
