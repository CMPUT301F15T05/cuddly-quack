package ca.ualberta.cs.swapmyride.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.location.LocationManager;

import java.util.List;

/**
 * Created by adrianomarini on 15-11-20.
 */
public class Geolocation {
    static final int MY_PERMISSIONS_REQUEST__COARSE_LOCATION = 1;
    static final int MY_PERMISSIONS_REQUEST__FINE_LOCATION = 1;

    private Location currentLocation;
    private LocationManager lm;

    /**
     * The contstructor needs a Context and a current Activity to ensure
     * that permissions are correct. This solves permission issues that
     * would exist in API 23 -- as adding permissions to manifest does not
     * solve issues in this (our target) API.
     * @param context
     * @param activity
     */

    public Geolocation(Context context, Activity activity) {
        // http://developer.android.com/training/permissions/requesting.html
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST__COARSE_LOCATION);

        }
        // http://developer.android.com/training/permissions/requesting.html
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST__FINE_LOCATION);

        }
    }




}
