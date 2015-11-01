package ca.ualberta.cs.swapmyride;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-10-31.
 */

// https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class FeedAdapter extends ArrayAdapter<Vehicle> {
    public FeedAdapter(Context context, ArrayList<Vehicle> vehicle) {
        super(context, 0, vehicle);
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

        // Populate the data into the template view using the data object
        vehicleTitle.setText(vehicle.getName());
        quantity.setText(String.format("%d",vehicle.getQuantity()));
        category.setText(vehicle.getCategory().toString());
        quality.setText(vehicle.getQuality().toString());
        // Return the completed view to render on screen
        return convertView;
    }
}
