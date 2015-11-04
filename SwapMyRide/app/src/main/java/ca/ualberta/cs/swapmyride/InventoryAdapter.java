package ca.ualberta.cs.swapmyride;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Daniel on 2015-10-31.
 */

// https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class InventoryAdapter extends ArrayAdapter<Vehicle> {
    private Context context;
    public InventoryAdapter(Context context, ArrayList<Vehicle> vehicle) {
        super(context, 0, vehicle);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Vehicle vehicle = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.userinventoryitem, parent, false);
        }
        // Lookup view for data population
        TextView vehicleTitle = (TextView) convertView.findViewById(R.id.vehicleTitle);
        TextView quantity = (TextView) convertView.findViewById(R.id.quantity);
        TextView ispublic = (TextView) convertView.findViewById(R.id.category);
        ImageView image = (ImageView) convertView.findViewById(R.id.vehiclePicture);

        // Populate the data into the template view using the data object
        vehicleTitle.setText(vehicle.getName());

        // ? is a conditional operator, the first statement following the ? is what happens when the
        // condition evaluates to true, the statement following the : is what happens when it
        //evaluates to false.
        ispublic.setText(vehicle.getPublic()? "Yes":"No");
        quantity.setText(String.format("%d", vehicle.getQuantity()));

        //set the image in inventory
        image.setBackground(new BitmapDrawable(context.getResources(), vehicle.getPhoto().getImage()));

        // Return the completed view to render on screen
        return convertView;
    }
}
