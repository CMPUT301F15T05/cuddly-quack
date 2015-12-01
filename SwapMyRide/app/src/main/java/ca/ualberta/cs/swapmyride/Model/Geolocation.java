package ca.ualberta.cs.swapmyride.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by adrianomarini on 15-11-20.
 *
 * This class deals with all functions related to location
 * of items.
 *
 */
public class Geolocation {
    private LocationManager lm;

    public Boolean getPermission(Activity activity, Context context){
        // http://developer.android.com/training/permissions/requesting.html
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        // http://developer.android.com/training/permissions/requesting.html
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.s

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        if ((ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)&&(ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        return false;
    }


    /**
     * @param context
     * @param activity
     *
     * This method deals with finding the device's current location
     *
     * This is considered the default location for items
     *
     * Method for providers and location adapted from:
     * http://stackoverflow.com/questions/17591147/how-to-get-current-location-in-android
     * User: Boris Pawlowski (& Thomas Clemensen)           Accessed: 22-11-2015
     *
     * Method for finding address using Geocoder adapted from
     * http://stackoverflow.com/questions/9409195/how-to-get-complete-address-from-latitude-and-longitude
     * User: user370305                                    Accessed: 22-11-2015
     *
     */

    public Address getCurrentLocation(Context context, Activity activity) throws IllegalArgumentException {
        Address address = new Address(Locale.getDefault());
        lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Boolean permission = getPermission(activity, context);
        //Check which location providers are enabled
        //The two major ones are gps services and the mobile network
        Boolean gps = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Boolean network = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        Location lastKnownLocation = new Location("GPS");

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
            address.setPostalCode("Location Not Enabled");
            address.setLocality("No Location");
            address.setLatitude(0);
            address.setLongitude(0);
            return address;
        }

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED){
            address.setPostalCode("Location Not Enabled");
            address.setLocality("No Location");
            address.setLatitude(0);
            address.setLongitude(0);
            return address;
        }

        if (!gps && !network){
            throw new IllegalArgumentException();
        }

        if (gps) {
            lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        //If GPS is not active, use the network to get a location
        if (!gps && network) {
            lastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if(lastKnownLocation == null){
            address.setPostalCode("Location Error");
            address.setLocality("Location Error");
            address.setCountryName("Location Error");
            address.setLatitude(0);
            address.setLongitude(0);
        }

        //If it is not null, try to get an address from the lat/long
        // that is returned -- Using Geocoder
        else{
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            double longitude = lastKnownLocation.getLongitude();
            double latitude = lastKnownLocation.getLatitude();

            address.setLongitude(longitude);
            address.setLatitude(latitude);

            //Get one possible address
            List<Address> addresses;
            try{
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e){
                address.setPostalCode("Location Error");
                address.setLocality("Location Error");
                address.setCountryName("Location Error");
                address.setLatitude(0);
                address.setLongitude(0);
                return address;
            } catch (IllegalArgumentException e){
                address.setPostalCode("Location Error");
                address.setLocality("Location Error");
                address.setCountryName("Location Error");
                address.setLatitude(0);
                address.setLongitude(0);
                return address;
            }

            //set attributes from this one possible address
            if(addresses != null && addresses.size() == 1){
                address.setLocality(addresses.get(0).getLocality());
                address.setAddressLine(0, addresses.get(0).getAddressLine(0));
                address.setPostalCode(addresses.get(0).getPostalCode());
                address.setAdminArea(addresses.get(0).getAdminArea());
                address.setCountryName(addresses.get(0).getCountryName());}
        }
        return address;
    }

    public Address setSpecificLocation(Context context, Activity activity, String myLocation){
        Address address = new Address(Locale.getDefault());
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try{
            addresses = geocoder.getFromLocationName(myLocation, 1);
            address.setLocality(addresses.get(0).getLocality());
            address.setCountryName(addresses.get(0).getCountryName());
            address.setAdminArea(addresses.get(0).getAdminArea());
            address.setAddressLine(0, addresses.get(0).getAddressLine(0));
            address.setPostalCode(myLocation);
            address.setLatitude(addresses.get(0).getLatitude());
            address.setLongitude(addresses.get(0).getLongitude());
        }
        catch(IOException e){
            address.setPostalCode("Location Error");
            address.setLocality("Location Error");
            address.setCountryName("Location Error");
            address.setLatitude(0);
            address.setLongitude(0);
            return address;
        }
        catch (IllegalArgumentException e){
            address.setPostalCode("Location Error");
            address.setLocality("Location Error");
            address.setCountryName("Location Error");
            address.setLatitude(0);
            address.setLongitude(0);
            return address;
        }
        catch (IndexOutOfBoundsException e){
            address.setPostalCode("Location Error");
            address.setLocality("Location Error");
            address.setCountryName("Location Error");
            address.setLatitude(0);
            address.setLongitude(0);
            return address;
        }
        return address;
    }
}
