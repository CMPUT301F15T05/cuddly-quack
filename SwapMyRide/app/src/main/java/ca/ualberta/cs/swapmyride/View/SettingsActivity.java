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
import android.util.Log;
import android.widget.Switch;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.R;

/**
 * View which allows user to change settings in the app. For instance, whether or not to download photos.
 */
public class SettingsActivity extends AppCompatActivity {

    Toolbar toolbar;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        aSwitch = (Switch) findViewById(R.id.downloadImages);
        setSupportActionBar(toolbar);
        aSwitch.setChecked(UserSingleton.getCurrentUser().getDownloadImages());
    }

    @Override
    protected void onPause() {
        super.onPause();
        UserSingleton.getCurrentUser().setDownloadImages(aSwitch.isChecked());
        Log.i("Settings", "User download preference: " +
                String.valueOf(UserSingleton.getCurrentUser().getDownloadImages()));
    }

}
