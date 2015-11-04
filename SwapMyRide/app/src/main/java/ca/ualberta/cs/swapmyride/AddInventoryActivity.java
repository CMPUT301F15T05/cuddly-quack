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
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.util.ArrayList;

public class AddInventoryActivity extends AppCompatActivity {

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

    DataManager dm;

    int position;

    //TODO THIS IS FROM GOOGLE DEV PHOTOS SIMPLY PAGE
    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinventory);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        // TODO: Needs to smell more MVCish
        vehicleImage = (ImageButton) findViewById(R.id.vehicleImage);
        vehicleName = (EditText) findViewById(R.id.vehicleField);
        vehicleQuantity = (EditText) findViewById(R.id.quantityField);
        vehicleComments = (EditText) findViewById(R.id.commentsField);
        vehiclePublic = (Switch) findViewById(R.id.ispublic);
        done = (Button) findViewById(R.id.button);
        dm = new DataManager(AddInventoryActivity.this);
        vehicle = new Vehicle();

        //Setup the spinners for category and quality
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<VehicleCategory>(this, android.R.layout.simple_spinner_dropdown_item, VehicleCategory.values()));
        // Biraj Zalavadia; http://stackoverflow.com/questions/21600781/onitemclicklistener-of-spinner; 2015-11-01
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
            //vehicleImage = loaded.getPicture()?
            vehicleImage.setBackground(new BitmapDrawable(getResources(), loaded.getPhoto().getImage()));
            vehicleName.setText(loaded.getName());
            vehicleQuantity.setText(loaded.getQuantity().toString());
            vehicleComments.setText(loaded.getComments());
            vehiclePublic.setChecked(loaded.getPublic());
            qualitySpinner.setSelection(loaded.getQuality().getPosition());
            categorySpinner.setSelection(loaded.getCategory().getPosition());
        }

        vehicleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });


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
                    //vehicle = UserSingleton.getCurrentUser().getInventory().getList().get(position);
                    //UserSingleton.getCurrentUser().getInventory().delete(vehicle);
                    UserSingleton.getCurrentUser().getInventory().getList().add(position, vehicle);
                    UserSingleton.getCurrentUser().getInventory().getList().remove(position+1);
                }
                else {
                    UserSingleton.getCurrentUser().addItem(vehicle);
                }
                //save the user to ensure all changes are updated
                dm.saveUser(UserSingleton.getCurrentUser());

                //dont start a new activity if we are editing a vehicle
                if(!loadVehicle) {
                    Intent intent = new Intent(AddInventoryActivity.this, MainMenu.class);
                    startActivity(intent);
                }
                finish();
            }
        });

    }

    //TODO THESE FUNCTIONS ARE MODIFIED FROM GOOGLE TAKING PHOTOS SIMPLY
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

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
}
