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
 * This is the tab where the feeds of items will appear that a person can
 * browse and select from. All of the items here are items from someone else's
 * inventory.
 *
 * Has toolbar at the top with search, add friend, and settings menu functionality!
 *
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

import ca.ualberta.cs.swapmyride.Adapter.FeedAdapter;
import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.User;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;
import ca.ualberta.cs.swapmyride.Controller.UserController;

public class Tab1 extends Fragment {

    ListView inventory;
    ArrayList<Vehicle> arrayOfVehicle;
    InventoryList inventoryList;
    FeedAdapter adapter;
    UserController uController;
    User user;

    /**
     * onCreate gets all of the items to be displayed, and makes them visible and individually
     * clickable.
     *
     * This is also where notifications are held -- the app will only notify a user on this
     * screen to prevent occlusion of other activities.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab1, container, false);
        uController = new UserController(getActivity());
        arrayOfVehicle = new ArrayList<>();
        adapter = new FeedAdapter(getActivity(), arrayOfVehicle);
        //send notification to user when screen is returned to this area.
        user = uController.getCurrentUser();
        user.getNotificationManager().notifyMe(getContext());
        new DataManager(getContext()).saveUser(user);  // TODO: these should be put into a Notification controller
        UserSingleton.addCurrentUser(user);  // TODO: these should be put into a Notification controller

        inventory = (ListView) v.findViewById(R.id.feedView);

        inventory.setAdapter(adapter);

        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ViewFeedInventoryActivity.class);
                UserSingleton.setFeedViewVehicle(arrayOfVehicle.get(position));
                startActivity(intent);
            }
        });

        return v;
    }

    /**
     * Quite similar to the above -- but checking for changes when the user returns to the screen.
     */

    @Override
    public void onResume() {
        super.onResume();

        inventoryList = new InventoryList();
        uController.updateFriends();

        inventoryList = uController.getFriendVehicles();

        arrayOfVehicle = inventoryList.getList();
        adapter = new FeedAdapter(getContext(), arrayOfVehicle);
        inventory.setAdapter(adapter);
        user.getNotificationManager().notifyMe(getContext());
        new DataManager(getContext()).saveUser(user);  // TODO: these should be put into a Notification controller
        UserSingleton.addCurrentUser(user);  // TODO: these should be put into a Notification controller

    }

    public ListView getInventoryList(){
        return inventory;
    }

}