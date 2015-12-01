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

/**
 * This is the tab where the feed of the items in a user's personal inventory will appear
 * There is a button to add new items to inventory, and as well, the option to click on each
 * item and view it in more detail.
 * <p/>
 * Created by Daniel on 2015-10-24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.InventoryAdapter;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

public class Tab2 extends Fragment {

    Button addInventory;
    ListView inventory;
    ArrayList<Vehicle> arrayOfVehicle;
    InventoryList inventoryList;
    InventoryAdapter adapter;
    UserController uController;

    /**
     * Gets all items from the inventory of the user and displays them in order.
     *
     * OnClick listener is present to take the user to a detail view if they select
     * an item
     *
     * OnClick listener is present if they click the plus to add a new item -- taken
     * to the add item view.
     *
     * Has toolbar at the top with search, add friend, and settings menu functionality!
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab2, container, false);

        addInventory = (Button) v.findViewById(R.id.addInventory);
        uController = new UserController(getContext());

        inventoryList = uController.getCurrentUser().getInventory();

        arrayOfVehicle = inventoryList.getList();

        adapter = new InventoryAdapter(getActivity(), arrayOfVehicle);

        inventory = (ListView) v.findViewById(R.id.inventoryView);

        inventory.setAdapter(adapter);

        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ViewVehicleActivity.class);

                //add the vehicle that has been selected to the intent to pass
                intent.putExtra("vehiclePosition", position);
                startActivity(intent);
            }
        });

        addInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddInventoryActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    public void onResume() {
        super.onResume();
        inventoryList = uController.getCurrentUser().getInventory();
        arrayOfVehicle = inventoryList.getList();
        adapter = new InventoryAdapter(getActivity(), arrayOfVehicle);
        inventory.setAdapter(adapter);
    }

    public Button getAddInventory() {
        return addInventory;
    }
}