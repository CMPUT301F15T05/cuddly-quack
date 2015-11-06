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

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * This class specifically works to add a vehicle to a user's inventory
 *
 * There is opportunity to input all information and a photo for the new
 * vehicle, and then it is added to the inventory list.
 */

public class AddInventoryActivity extends AppCompatActivity {

    static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    Toolbar toolbar;
    Spinner categorySpinner;
    VehicleCategory vehicleCategory;
    Spinner qualitySpinner;
    VehicleQuality vehicleQuality;

    VehicleController vehicleController;
    Vehicle vehicle;

    ImageButton vehicleImage;
    EditText vehicleName;
    EditText vehicleQuantity;
    EditText vehicleComments;
    Switch vehiclePublic;
    Button done;
    UserController uController;
    Button delete;
    DataManager dm;

    int position;

    //TODO THIS IS FROM GOOGLE DEV PHOTOS SIMPLY PAGE
    static final int REQUEST_IMAGE_CAPTURE = 1;

    /**
     * In this function (prescribed by Android), we collect all information
     * about the new vehicle and input it into a vehicle object.
     * This is then saved.
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
        vehicleImage = (ImageButton) findViewById(R.id.vehicleImage);
        delete = (Button) findViewById(R.id.delete);
        vehicleName = (EditText) findViewById(R.id.vehicleField);
        vehicleQuantity = (EditText) findViewById(R.id.quantityField);
        vehicleComments = (EditText) findViewById(R.id.commentsField);
        vehiclePublic = (Switch) findViewById(R.id.category);
        done = (Button) findViewById(R.id.button);
        dm = new DataManager(AddInventoryActivity.this);
        vehicle = new Vehicle();

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
        if(loadVehicle){
            position = getIntent().getIntExtra("vehiclePosition", 0);
            loaded = UserSingleton.getCurrentUser().getInventory().getList().get(position);
            vehicle.setPhoto(loaded.getPhoto());
            vehicleImage.setBackground(new BitmapDrawable(getResources(), loaded.getPhoto().getImage()));
            vehicleName.setText(loaded.getName());
            vehicleQuantity.setText(loaded.getQuantity().toString());
            vehicleComments.setText(loaded.getComments());
            vehiclePublic.setChecked(loaded.getPublic());
            qualitySpinner.setSelection(loaded.getQuality().getPosition());
            categorySpinner.setSelection(loaded.getCategory().getPosition());
        }
        //default the photo to a new photo if we are not loading a vehicle
        else{
            vehicle.setPhoto(new Photo(getApplicationContext()));
        }

        /**
         * This onClick listener implements the function that clicking on the default
         * image box at the top of the vehicle page will open the camera and allow the
         * user to take a photo which will be saved directly to the vehicle object
         */
        vehicleImage.setOnClickListener(new View.OnClickListener() {
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
                vehicle.setPhoto(new Photo(getApplicationContext()));
                vehicleImage.setBackground(new BitmapDrawable(getResources(), vehicle.getPhoto().getImage()));


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
                vehicle.setName(vehicleName.getText().toString());
                Log.i("Vehicle Name", vehicleName.getText().toString());
                vehicle.setCategory(vehicleCategory);
                vehicle.setQuality(vehicleQuality);

                //http://docs.oracle.com/javase/8/docs/api/java/lang/Integer.html#parseInt-java.lang.String-int-
                //Nov. 3/ 2015
                //the error should never occur as we force the user to input a numeric value, but
                //we are required to catch it anyway
                try {
                    vehicle.setQuantity(Integer.parseInt(vehicleQuantity.getText().toString()));
                }catch(NumberFormatException e){
                    e.printStackTrace();
                }
                vehicle.setComments(vehicleComments.getText().toString());
                vehicle.setPublic(vehiclePublic.isChecked());


                //add the vehicle to our current user.
                if(loadVehicle){
                    //UserSingleton.getCurrentUser().getInventory().getList().add(position, vehicle);
                    uController.getUserInventoryItems().add(position,vehicle);
                    //UserSingleton.getCurrentUser().getInventory().getList().remove(position+1);
                    uController.getUserInventoryItems().remove(position+1);
                }
                else {
                    //UserSingleton.getCurrentUser().addItem(vehicle);
                    uController.getCurrentUser().addItem(vehicle);
                }
                //save the user to ensure all changes are updated
                uController.saveCurrentUser();

                /*
                //dont start a new activity if we are editing a vehicle
                if(!loadVehicle) {
                    Intent intent = new Intent(AddInventoryActivity.this, MainMenu.class);
                    startActivity(intent);
                }*/
                finish();
            }
        });

        vehicleImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vehicle.getPhoto().deleteImage(getApplicationContext());
                vehicleImage.setBackground(new BitmapDrawable(getResources(), vehicle.getPhoto().getImage()));
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

                // Show an expanation to the user *asynchronously* -- don't block
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    /**
     * After returning from the camera activity, get the photo information
     * and send it into the structure to get it ready.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Photo photo = new Photo(imageBitmap);
            vehicle.setPhoto(photo);
            vehicleImage.setBackground(new BitmapDrawable(getResources(), imageBitmap));
        }
    }

    /**
     * checking if the hardware has a camera that can be used.
     *
     * @param context
     * @return true or false
     */

    public boolean checkHasCamera(Context context){
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
}
