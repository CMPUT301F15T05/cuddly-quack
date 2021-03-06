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
package ca.ualberta.cs.swapmyride.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Controller.DataManager;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.View.AddFriendProfileActivity;

/**
 * Handles notifying users of new trades, new friends, and new borrows.
 */
public class NotificationManager {

    private ArrayList<Trade> tradesToBeNotified = new ArrayList<>();
    private ArrayList<String> friendRequests = new ArrayList<>();

    public NotificationManager() {
    }

    public Boolean notifyTrade(Trade trade) {
        tradesToBeNotified.add(trade);
        return true;
    }

    public Boolean removeTrade(Trade trade) {
        tradesToBeNotified.remove(trade);
        return true;
    }

    /**
     * @param username String of username that has added the current user
     */
    public void notifyFriendRequest(String username) {
        friendRequests.add(username);
    }

    //http://stackoverflow.com/questions/2115758/how-to-display-alert-dialog-in-android
    // Author: David Hedlund
    // Modified and Accessed 4 November 2015
    public void showTrade(Context context) {
        Integer size = tradesToBeNotified.size();
        String trades = Integer.toString(size);
        if (trades == "1") {
            new AlertDialog.Builder(context)
                    .setTitle("New Trade!")
                    .setMessage("You have " + trades + " new trade pending! Go to Pending Trades to view!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })
                    .show();
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("New Trade!")
                    .setMessage("You have " + trades + " new trades pending! Go to Pending Trades to view!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })
                    .show();
        }
        tradesToBeNotified.clear();
    }

    /**
     * When logging in, called to show user who has followed them since their last login.
     * @param username String of the username that will be displayed in the notification. ie, the user that has added a new friend, which is the current user.
     */
    public void showFriendRequest(final Context context, final String username) {
        new AlertDialog.Builder(context)
                .setTitle("New Friend!")
                .setMessage(username + " is now following you! Click to view their profile!")
                .setPositiveButton("View Profile", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, AddFriendProfileActivity.class);
                        intent.putExtra("Username", username);
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
        friendRequests.clear();
    }


    /**
     * Called when a user loads the first tab of the application. This corresponds to them logging in usually.
     * This will then check whether the user has trade and/or friend notifications to display.
     */
    public void notifyMe(Context context) {
        if (tradesToBeNotified.size() > 0) {
            showTrade(context);
        }

        for (String username : friendRequests) {
            showFriendRequest(context, username);
        }

        friendRequests.clear();
        DataManager dm = new DataManager(context);
        dm.saveUser(UserSingleton.getCurrentUser());

    }

}
