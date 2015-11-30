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
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Misc.DefaultPhotoSingleton;
import ca.ualberta.cs.swapmyride.Misc.UniqueID;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

/**
 * Viewing inventory based on a click of a vehicle in the feed.
 */

public class ViewFeedInventoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button trade;
    ImageView vehiclePicture;
    LinearLayout gallery;
    TextView title;
    TextView quantity;
    TextView category;
    TextView quality;
    EditText comments;
    Vehicle vehicle;
    TextView postal;
    TextView lat;
    TextView longit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedinventory);
        LocalDataManager ldm = new LocalDataManager(getApplicationContext());

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        //vehiclePicture = (ImageView) findViewById(R.id.picture);
        gallery = (LinearLayout)findViewById(R.id.feedinventorygallery);



        title = (TextView) findViewById(R.id.title);
        quantity = (TextView) findViewById(R.id.quality);
        category = (TextView) findViewById(R.id.category);
        quality = (TextView) findViewById(R.id.quantity);
        comments = (EditText) findViewById(R.id.commentsHead);
        postal = (TextView) findViewById(R.id.location);
        lat = (TextView) findViewById(R.id.Latitude);
        longit = (TextView) findViewById(R.id.Longitude);

        vehicle = UserSingleton.getFeedViewVehicle();

        getSupportActionBar().setTitle(vehicle.getName());

        //vehiclePicture.setBackground(new BitmapDrawable(getResources(), vehicle.getPhotoArrayList().get(0).getImage()));
        gallery.removeAllViews();
        //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
        //load the picture from the first
        for (UniqueID uid : vehicle.getPhotoIds()){
            Photo photo;
            if(UserSingleton.getDownloadPhotos()){
                photo = ldm.loadPhoto(uid.getID());
            }
            else{
                photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
            }
            ImageView newImage = new ImageView(getApplicationContext());
            newImage.setImageBitmap(photo.getImage());
            newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            newImage.setAdjustViewBounds(true);
            gallery.addView(newImage);
        }


        title.setText(vehicle.getName());
        quantity.setText(String.format("%d", vehicle.getQuantity()));
        category.setText(vehicle.getCategory().getCategory());
        quality.setText(vehicle.getQuality().getQuality());
        comments.setText(vehicle.getComments());
        postal.setText(vehicle.getLocation().getPostalCode() + ", "+vehicle.getLocation().getLocality());
        lat.setText(String.valueOf(vehicle.getLocation().getLatitude()));
        longit.setText(String.valueOf(vehicle.getLocation().getLongitude()));


        trade = (Button) findViewById(R.id.trade);
        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFeedInventoryActivity.this, FeedTradeActivity.class);
                intent.putExtra("Username", vehicle.getBelongsTo());
                startActivity(intent);
                finish();
            }
        });
    }

    public TextView getTheName(){
        return title;
    }
}
