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
package ca.ualberta.cs.swapmyride.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.ListView;

import ca.ualberta.cs.swapmyride.View.Tab1;
import ca.ualberta.cs.swapmyride.View.Tab2;
import ca.ualberta.cs.swapmyride.View.Tab3;

/**
 *
 * http://www.android4devs.com/2015/01/how-to-make-material-design-sliding-tabs.html
 *
 * Imported from AndroidForDevs
 *
 * Helps the sliding behaviour of the tabs to work properly.
 *
 * Created by Daniel on 2015-10-24.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            tab1 = new Tab1();
            return tab1;
        }
        else if (position == 1)          // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            tab2 = new Tab2();
            return tab2;
        }

        else {
            tab3 = new Tab3();
            return tab3;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    public ListView getInventoryListView(){
        return tab1.getInventoryList();
    }

}