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
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Viewing inventory based on a click of a vehicle in the feed.
 */

public class ViewFeedInventory extends AppCompatActivity {

    Toolbar toolbar;
    Button trade;
    ImageView vehiclePicture;
    TextView title;
    TextView quanitiy;
    TextView category;
    TextView quality;
    TextView comments;
    Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedinventory);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        vehiclePicture = (ImageView) findViewById(R.id.picture);
        title = (TextView) findViewById(R.id.title);
        quanitiy = (TextView) findViewById(R.id.quality);
        category = (TextView) findViewById(R.id.category);
        quality = (TextView) findViewById(R.id.quantity);
        comments = (TextView) findViewById(R.id.commentsHead);

        vehicle = UserSingleton.getFeedViewVehicle();

        vehiclePicture.setBackground(new BitmapDrawable(getResources(), vehicle.getPhoto().getImage()));
        title.setText(vehicle.getName());
        quanitiy.setText(vehicle.getQuantity().toString());
        category.setText(vehicle.getCategory().getCategory());
        quality.setText(vehicle.getQuality().getQuality());
        comments.setText(vehicle.getComments());

        trade = (Button) findViewById(R.id.trade);
        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFeedInventory.this, FeedTradeActivity.class);
                intent.putExtra("Username", vehicle.getBelongsTo());
                startActivity(intent);
            }
        });
    }
}
