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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.UserController;
import ca.ualberta.cs.swapmyride.Misc.DefaultPhotoSingleton;
import ca.ualberta.cs.swapmyride.Misc.UniqueID;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Misc.VehicleCategory;
import ca.ualberta.cs.swapmyride.Misc.VehicleQuality;
import ca.ualberta.cs.swapmyride.Model.Geolocation;
import ca.ualberta.cs.swapmyride.Model.Photo;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

/**
 * This class specifically works to add a vehicle to a user's inventory
 * <p/>
 * There is opportunity to input all information and a photo for the new
 * vehicle, and then it is added to the inventory list.
 */

public class AddInventoryActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    //TODO THIS IS FROM GOOGLE DEV PHOTOS SIMPLY PAGE
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Toolbar toolbar;
    Spinner categorySpinner;
    VehicleCategory vehicleCategory;
    Spinner qualitySpinner;
    VehicleQuality vehicleQuality;
    Vehicle vehicle;
    LinearLayout gallery;
    EditText vehicleName;
    EditText vehicleQuantity;
    EditText vehicleComments;
    Switch vehiclePublic;
    Button done;
    UserController uController;
    Button delete;
    EditText location;
    ArrayList<Photo> photos = new ArrayList<>();
    int position;
    Address current;
    Geolocation geolocation;

    /**
     * In this function (prescribed by Android), we collect all information
     * about the new vehicle and input it into a vehicle object.
     * This is then saved.
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinventory);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        uController = new UserController(getApplicationContext());
        // TODO: Needs to smell more MVCish
        //vehicleImage = (ImageButton) findViewById(R.id.vehicleImage);
        gallery = (LinearLayout) findViewById(R.id.addinventorygallery);
        delete = (Button) findViewById(R.id.delete);
        vehicleName = (EditText) findViewById(R.id.vehicleField);
        vehicleQuantity = (EditText) findViewById(R.id.quantityField);
        vehicleComments = (EditText) findViewById(R.id.commentsField);
        vehiclePublic = (Switch) findViewById(R.id.ispublic);
        done = (Button) findViewById(R.id.button);
        location = (EditText) findViewById(R.id.locationField);
        vehicle = new Vehicle();

        //Assign and display the current location
        geolocation = new Geolocation();
        try {
            current = geolocation.getCurrentLocation(getApplicationContext(), this);
            location.setText(current.getPostalCode());
        } catch (IllegalArgumentException e) {
            location.setText("Geolocation cannot be determined.");
        }

        /**
         * Using spinners to select category and quality of a vehicle - taking from the enumeration
         * classes we have elsewhere. This function was adapted from Biraj Zalavadia on StackOverflow
         * Accessed 2015-11-01
         * @see <a href="http://stackoverflow.com/questions/21600781/onitemclicklistener-of-spinner">stackOverflow</a>
         */
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<VehicleCategory>(this, android.R.layout.simple_spinner_dropdown_item, VehicleCategory.values()));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vehicleCategory = (VehicleCategory) categorySpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // What if nothing selected?
            }
        });

        /**
         * Using spinners to select category and quality of a vehicle - taking from the enumeration
         * classes we have elsewhere. This function was adapted from Biraj Zalavadia on StackOverflow
         * Accessed 2015-11-01
         * @see <a href="http://stackoverflow.com/questions/21600781/onitemclicklistener-of-spinner">stackOverflow</a>
         */
        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        qualitySpinner.setAdapter(new ArrayAdapter<VehicleQuality>(this, android.R.layout.simple_spinner_dropdown_item, VehicleQuality.values()));
        qualitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                vehicleQuality = (VehicleQuality) qualitySpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // What if nothing selected?
            }
        });

        final boolean loadVehicle = getIntent().hasExtra("vehiclePosition");
        Vehicle loaded;
        if (loadVehicle) {
            position = getIntent().getIntExtra("vehiclePosition", 0);
            loaded = UserSingleton.getCurrentUser().getInventory().getList().get(position);


            //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
            //load the picture from the first
            vehicle.setPhotoIds(loaded.getPhotoIds());

            vehicleName.setText(loaded.getName());
            vehicleQuantity.setText(loaded.getQuantity().toString());
            vehicleComments.setText(loaded.getComments());
            vehiclePublic.setChecked(loaded.getPublic());
            qualitySpinner.setSelection(loaded.getQuality().getPosition());
            categorySpinner.setSelection(loaded.getCategory().getPosition());
            // TODO: Fix null error issue
            //location.setText(loaded.getLocation().getPostalCode());
            LocalDataManager ldm = new LocalDataManager(getApplicationContext());
            for (UniqueID uid : vehicle.getPhotoIds()) {
                photos.add(ldm.loadPhoto(uid.getID()));
            }
            for (Photo photo : photos) {
                ImageView newImage = new ImageView(getApplicationContext());
                newImage.setImageBitmap(photo.getImage());
                newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                newImage.setAdjustViewBounds(true);
                gallery.addView(newImage);
            }

        }

        //default the photo to a new photo if we are not loading a vehicle
        else {
            // TODO: Default photo? Here or set in Vehicle?
            Photo photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
            ImageView newImage = new ImageView(getApplicationContext());
            newImage.setImageBitmap(photo.getImage());
            newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            newImage.setAdjustViewBounds(true);
            gallery.addView(newImage);
            vehicle.deletePhotoArrayList(getApplicationContext());
        }

        LocalDataManager ldm = new LocalDataManager(getApplicationContext());

        //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
        //load the picture from the first
        for (UniqueID uid : vehicle.getPhotoIds()) {
            Photo photo;
            if (UserSingleton.getDownloadPhotos()) {
                photo = ldm.loadPhoto(uid.getID());
            } else {
                photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
            }
            ImageView newImage = new ImageView(getApplicationContext());
            newImage.setImageBitmap(photo.getImage());
            newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            newImage.setAdjustViewBounds(true);
            gallery.addView(newImage);
        }
        /**
         * This onClick listener implements the function that clicking on the default
         * image box at the top of the vehicle page will open the camera and allow the
         * user to take a photo which will be saved directly to the vehicle object
         */
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });

        /**
         * This onClick listener occurs when someone clicks delete. This will delete the photo
         * that is initialized in the view.
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vehicle.setPhoto(new Photo(getApplicationContext()));
                //vehicleImage.setBackground(new BitmapDrawable(getResources(), vehicle.getPhoto().getImage()));
                vehicle.deletePhotoArrayList(getApplicationContext());
                gallery.removeAllViews();
                Photo photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
                ImageView newImage = new ImageView(getApplicationContext());
                newImage.setImageBitmap(photo.getImage());
                newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                newImage.setAdjustViewBounds(true);
                gallery.addView(newImage);
                LocalDataManager ldm = new LocalDataManager(getApplicationContext());
                for (Photo photo1 : photos) {
                    ldm.deletePhoto(photo1.getUid().getID());
                }
                photos.clear();

                //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
                //load the picture from the first
                if (vehicle.getPhotoIds().size() > 0 && UserSingleton.getDownloadPhotos()) {
                    gallery.removeAllViews();
                }

                for (UniqueID uid : vehicle.getPhotoIds()) {
                    if (UserSingleton.getDownloadPhotos()) {
                        photo = ldm.loadPhoto(uid.getID());
                    }
                    newImage = new ImageView(getApplicationContext());
                    newImage.setImageBitmap(photo.getImage());
                    newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    newImage.setAdjustViewBounds(true);
                    gallery.addView(newImage);
                }
            }
        });

        /**
         * This onClick listener occurs after done button is clicked. This would save
         * the object to inventory -- which then makes it appear in the inventory tab.
         *
         * Taken from Oracle Documentation - we found we were required to catch the
         * possibility that a number might be the wrong format, or not even a number
         * at all. Accessed November 3, 2015.
         *
         * @see <a href="http://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-java.lang.String-int-">Oracle</a>
         */

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vehicle.setPhoto(vehicleImage);
                Geolocation geolocation1 = new Geolocation();
                LocalDataManager ldm = new LocalDataManager(getApplicationContext());

                if (vehicleName.getText().toString().equals("")) {
                    Toast.makeText(AddInventoryActivity.this, "Please enter name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (vehicleQuantity.getText().toString().equals("0") || vehicleQuantity.getText().toString().equals("")) {
                    Toast.makeText(AddInventoryActivity.this, "Quantity cannot be 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                vehicle.setName(vehicleName.getText().toString());

                vehicle.setCategory(vehicleCategory);
                vehicle.setQuality(vehicleQuality);
                vehicle.setLocation(geolocation1.setSpecificLocation(getApplicationContext(),
                        AddInventoryActivity.this, location.getText().toString()));

                //http://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-java.lang.String-int-
                //Nov. 3/ 2015
                //the error should never occur as we force the user to input a numeric value, but
                //we are required to catch it anyway
                try {
                    vehicle.setQuantity(Integer.parseInt(vehicleQuantity.getText().toString()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                vehicle.setComments(vehicleComments.getText().toString());
                vehicle.setPublic(vehiclePublic.isChecked());
                vehicle.setBelongsTo(UserSingleton.getCurrentUser().getUserName());

                for (Photo photo : photos) {
                    vehicle.addPhoto(photo.getUid());
                    ldm.savePhoto(photo);
                }

                //add the vehicle to our current user.
                if (loadVehicle) {
                    //UserSingleton.getCurrentUser().getInventory().getList().add(position, vehicle);
                    uController.getUserInventoryItems().add(position, vehicle);
                    //UserSingleton.getCurrentUser().getInventory().getList().remove(position+1);
                    uController.getUserInventoryItems().remove(position + 1);
                } else {
                    //UserSingleton.getCurrentUser().addItem(vehicle);
                    uController.getCurrentUser().addItem(vehicle);
                }
                //save the user to ensure all changes are updated
                uController.saveCurrentUser();
                Gson gson = new Gson();
                String s = gson.toJson(vehicle);
                Log.i("SizeOfCar", Integer.toString(s.length()));

                /*
                //dont start a new activity if we are editing a vehicle
                if(!loadVehicle) {
                    Intent intent = new Intent(AddInventoryActivity.this, MainMenu.class);
                    startActivity(intent);
                }*/
                finish();
            }
        });

        gallery.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vehicle.deletePhotoArrayList(getApplicationContext());
                gallery.removeAllViews();
                LocalDataManager ldm = new LocalDataManager(getApplicationContext());

                //TODO UPDATE THIS LINE TO UPDATE THE FEED WITH THE VEHICLES FIRST PICTURE
                //load the picture from the first
                for (UniqueID uid : vehicle.getPhotoIds()) {
                    Photo photo;
                    if (UserSingleton.getDownloadPhotos()) {
                        photo = ldm.loadPhoto(uid.getID());
                    } else {
                        photo = DefaultPhotoSingleton.getInstance().getDefaultPhoto();
                    }
                    ImageView newImage = new ImageView(getApplicationContext());
                    newImage.setImageBitmap(photo.getImage());
                    newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    newImage.setAdjustViewBounds(true);
                    gallery.addView(newImage);
                }
                return true;
            }
        });

    }

    //TODO THESE FUNCTIONS ARE MODIFIED FROM GOOGLE TAKING PHOTOS SIMPLY

    /**
     * This calls the activity to take the photo.
     */

    private void dispatchTakePictureIntent() {

        // http://developer.android.com/training/permissions/requesting.html
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(AddInventoryActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddInventoryActivity.this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(AddInventoryActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {

            Log.i("TakingPictureIntent", "Trying to take a photo");
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null &&
                    checkHasCamera(getApplicationContext())) {

                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * After returning from the camera activity, get the photo information
     * and send it into the structure to get it ready.
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Photo photo = new Photo(imageBitmap);
            ImageView newImage = new ImageView(getApplicationContext());
            newImage.setImageBitmap(photo.getImage());
            newImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            newImage.setAdjustViewBounds(true);
            //gallery.removeAllViews();
            gallery.addView(newImage);
            photos.add(photo);
        }

    }

    /**
     * checking if the hardware has a camera that can be used.
     *
     * @return True if camera, false if not.
     */

    public boolean checkHasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    public EditText getVehicleName() {
        return (EditText) findViewById(R.id.vehicleField);
    }

    public Button getSaveButton() {
        return (Button) findViewById(R.id.save);
    }
}
