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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Misc.DefaultPhotoSingleton;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

/**
 * Created by Daniel on 2015-10-31.
 */

// https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class FeedAdapter extends ArrayAdapter<Vehicle> {
    private Context context;

    public FeedAdapter(Context context, ArrayList<Vehicle> vehicle) {
        super(context, 0, vehicle);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicle vehicle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.friendsinventoryitem, parent, false);
        }
        // Lookup view for data population
        TextView vehicleTitle = (TextView) convertView.findViewById(R.id.vehicleTitle);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView category = (TextView) convertView.findViewById(R.id.category);
        TextView quality = (TextView) convertView.findViewById(R.id.quality);
        ImageView image = (ImageView) convertView.findViewById(R.id.vehiclePicture);
        // Populate the data into the template view using the data object
        vehicleTitle.setText(vehicle.getName());
        quantity.setText(String.format("%d", vehicle.getQuantity()));
        category.setText(vehicle.getCategory().getCategory());
        quality.setText(vehicle.getQuality().getQuality());

        //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
        //load the picture from the first
        LocalDataManager ldm = new LocalDataManager(context);
        Photo photo;
        if(vehicle.getPhotoIds().size() > 0) {
            photo = ldm.loadPhoto(vehicle.getPhotoIds().get(0).getID());
        }
        else{
           photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
        }
        image.setBackground(new BitmapDrawable(photo.getImage()));
        // Return the completed view to render on screen
        return convertView;
    }
}
