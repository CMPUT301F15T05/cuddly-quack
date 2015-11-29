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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.InventoryAdapter;
import ca.ualberta.cs.swapmyride.Misc.DistanceOption;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;
import ca.ualberta.cs.swapmyride.Controller.SearchController;

/**
 * Using a SearchController, allows searching through inventories
 * by entering a search term or a category to search for.
 */

public class SearchInventoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText searchField;
    Spinner categorySpinner;
    Button inventorySearch;
    ListView searchList;
    VehicleCategory vehicleCategory;
    ArrayList<Vehicle> inventoryList;
    SearchController searchController;
    ArrayList<Vehicle> foundVehicles;
    InventoryAdapter adapter;
    Spinner distance;
    DistanceOption desiredDistance;

    /**
     * OnCreate opens the opportunity for a user to enter a string or
     * select a category. This is then put into the SearchController, and
     * the returned ArrayList is displayed.
     *
     * The user has the option to search their own inventory, or search in general
     * -- hence the dual button interface.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchinventory);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        searchController = new SearchController();

        searchList = (ListView) findViewById(R.id.searchList);

        inventoryList = UserSingleton.getCurrentUser().getInventory().getList();

        searchField = (EditText) findViewById(R.id.searchField);
        inventorySearch = (Button) findViewById(R.id.inventorySearch);

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        categorySpinner.setAdapter(new ArrayAdapter<VehicleCategory>(this, android.R.layout.simple_spinner_dropdown_item, VehicleCategory.values()));

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vehicleCategory = (VehicleCategory) categorySpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // What if nothing selected?
                vehicleCategory = VehicleCategory.NONE;
            }
        });

        distance = (Spinner) findViewById(R.id.distanceSpinner);

        distance.setAdapter(new ArrayAdapter<DistanceOption>(this, android.R.layout.simple_spinner_dropdown_item, DistanceOption.values()));

        distance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                desiredDistance = (DistanceOption) distance.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // What if nothing selected?
                desiredDistance = DistanceOption.ONE_HUNDRED_KM;
            }
        });
            // TODO setonclicklistners to get the item description
            inventorySearch.setOnClickListener(new View.OnClickListener()

                                               {
                                                   @Override
                                                   public void onClick(View v) {

                                                       foundVehicles = searchController.findInventoryVehicle(searchField.getText().toString(), vehicleCategory, inventoryList,
                                                               SearchInventoryActivity.this, getApplicationContext(), (double) Integer.parseInt(desiredDistance.getDistance()));

                                                       adapter = new InventoryAdapter(getApplicationContext(), foundVehicles);

                                                       searchList.setAdapter(adapter);
                                                   }
                                               }

            );

        }

    }
