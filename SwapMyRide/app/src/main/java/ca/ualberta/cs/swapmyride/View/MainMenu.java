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

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ca.ualberta.cs.swapmyride.Adapter.ViewPagerAdapter;
import ca.ualberta.cs.swapmyride.Model.Geolocation;
import ca.ualberta.cs.swapmyride.R;

/**
 * Main menu is the central activity for our app. In this view,
 * we have three tabs: one for a feed of friends' items, one for
 * a feed of our own inventory, and one with a list of other activities
 * a user can do, such as viewing friends, editing profile, and seeing trades.
 */

public class MainMenu extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Feed","Inventory","User"};
    int Numboftabs =3;
    Geolocation geolocation = new Geolocation();

    /**
     * onCreate, the toolbars and tabs are created, and a user
     * can slide between tabs.
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        // Creating The Toolbar and setting it as the Toolbar for the activity

        geolocation.getPermission(this, this.getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    /**
     * We have the options button implemented here - this allows the menu to expand
     * and offer the settings menu
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    /**
     * as above, allows access to settings menu
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainMenu.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(MainMenu.this, SearchInventoryActivity.class);
            startActivity(intent);
            return true;

        }

        if (id == R.id.action_addfriend) {
            Intent intent = new Intent(MainMenu.this, SearchFriendsActivity.class);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public ListView getFeed(){
        return (ListView) findViewById(R.id.feedView);
    }

    public ListView getInventoryView(){
        return adapter.getInventoryListView();
    }

    public TextView getViewFriends(){
        return (TextView) findViewById(R.id.viewFriends);
    }

    public TextView getPastTrades(){
        return (TextView) findViewById(R.id.pastTrades);
    }

    public TextView getCurrentTrades(){
        return (TextView) findViewById(R.id.activeTrades);
    }

    public TextView getEditProfile(){
        return (TextView) findViewById(R.id.editProfile);
    }

    public SlidingTabLayout getTabs(){
        return (SlidingTabLayout) findViewById(R.id.tabs);
    }

    public ActionMenuItemView getActionAddFriend(){
        return (ActionMenuItemView) findViewById(R.id.action_addfriend);
    }

    public Tab2 getTab2(){
        return adapter.tab2;
    }
}
