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
public class FriendAdapter extends ArrayAdapter<User> {
    public FriendAdapter(Context context, ArrayList<User> user) {
        super(context, 0, user);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.frienditem, parent, false);
        }

        // TODO add image too imageView
        // Lookup view for data population
        TextView friendField = (TextView) convertView.findViewById(R.id.friendField);

        // Populate the data into the template view using the data object
        friendField.setText(user.getUserName());

        // Return the completed view to render on screen
        return convertView;
    }
}
