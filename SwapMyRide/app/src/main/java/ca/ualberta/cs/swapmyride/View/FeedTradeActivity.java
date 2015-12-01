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
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

public class FeedTradeActivity extends AppCompatActivity {

    Toolbar toolbar;
    String friendUsername;
    ListView feedMultipleView;
    Button trade;
    InventoryList friendInventory;
    ArrayList<String> vehicleNames;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedtrade);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        UserController userController = new UserController(getApplicationContext());

        Intent intent = this.getIntent();

        trade = (Button) findViewById(R.id.trade);
        feedMultipleView = (ListView) findViewById(R.id.feedMultipleView);

        friendUsername = intent.getStringExtra("Username");

        getSupportActionBar().setTitle(friendUsername+" Inventory");

        vehicleNames = new ArrayList<>();

        friendInventory = userController.getFriendVehicles(friendUsername);

        for (Vehicle vehicle: friendInventory.getList()) {
            if (vehicle.getPublic()) {
                vehicleNames.add(vehicle.getName());
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, vehicleNames);
        feedMultipleView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        feedMultipleView.setAdapter(adapter);

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            // http://theopentutorials.com/tutorials/android/listview/android-multiple-selection-listview/#ListViewMultipleSelectionActivityjava
            public void onClick(View v) {
                Trade currentTrade = new Trade();

                SparseBooleanArray checked = feedMultipleView.getCheckedItemPositions();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);

                    if (checked.valueAt(i)) {
                        // Adding items of friendInventory that are checked
                        currentTrade.addBorrowerItem(friendInventory.getList().get(position));
                    }
                }

                Intent intent = new Intent(getApplicationContext(), FeedTradeUserActivity.class);

                //TODO more MVC controller stuff
                // Setting trade data in UserSingleton
                currentTrade.setOwner(UserSingleton.getCurrentUser().getUserName());
                currentTrade.setBorrower(friendUsername);

                UserSingleton.setCurrentTrade(currentTrade);

                // start the ResultActivity
                startActivity(intent);

                finish();
            }
        });
    }
}
