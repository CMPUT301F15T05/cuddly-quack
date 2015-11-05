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

/**
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
import android.widget.ListView;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    ListView inventory;
    ArrayList<Vehicle> arrayOfVehicle;
    InventoryList inventoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab1,container,false);
        inventoryList = new InventoryList();
        User user = UserSingleton.getCurrentUser();

        for (User friend: user.getFriends().getFriendList()){
            InventoryList friendInventory = friend.getInventory();

            for (Vehicle vehicle: friendInventory.getList()) {
                if(vehicle.getPublic()){
                    inventoryList.add(vehicle);
                }
            }
        }


        arrayOfVehicle = inventoryList.getList();

        FeedAdapter adapter = new FeedAdapter(getActivity(), arrayOfVehicle);

        inventory = (ListView) v.findViewById(R.id.feedView);

        inventory.setAdapter(adapter);

        // TODO Pass state to ViewVehicleActivity!
        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ViewFeedInventory.class);
                startActivity(intent);
            }
        });
        return v;
    }
}