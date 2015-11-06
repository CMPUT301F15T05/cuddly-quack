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

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * This activity is designed to search friends and display friends that match
 * a inputted string.
 */

public class SearchFriendsActivity extends AppCompatActivity {

    SearchController searchController;

    Toolbar toolbar;
    EditText searchField;
    ImageButton searchButton;
    ListView friendView;
    ArrayList<User> foundUsers;
    FriendAdapter adapter;
    String username;

    /**
     * Displays a search field and accepts input to search for a username
     *
     * Uses a SearchController to search through the
     * information stored in the disk and return any
     * found friends matching the query.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfriends);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        searchController = new SearchController();

        foundUsers = new ArrayList<User>();

        searchField = (EditText) findViewById(R.id.searchField);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        friendView = (ListView) findViewById(R.id.friendView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = searchField.getText().toString();

                foundUsers = searchController.findUser(username, getApplicationContext());

                adapter = new FriendAdapter(getApplicationContext(), foundUsers);

                friendView.setAdapter(adapter);
            }
        });

        friendView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendsList friendsList = UserSingleton.getCurrentUser().getFriends();
                DataManager dataManager = new DataManager(getApplicationContext());
                User user = dataManager.loadUser(username);

                if (friendsList.hasUser(user)) {
                    Log.i("hasUser", "Friend has already been added");
                } else {
                    Intent intent = new Intent(getApplicationContext(), AddFriendProfileActivity.class);

                    //add the vehicle that has been selected to the intent to pass
                    intent.putExtra("Username", username);

                    startActivity(intent);
                }
            }
        });
    }

}
