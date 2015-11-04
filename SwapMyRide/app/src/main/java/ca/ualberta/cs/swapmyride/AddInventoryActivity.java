package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
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

    ImageButton vehicleImage;
    EditText vehicleName;
    EditText vehicleQuantity;
    EditText vehicleComments;
    Switch vehiclePublic;
    Button done;

    DataManager dm;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinventory);

        dm = new DataManager(AddInventoryActivity.this);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

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

        // TODO: Needs to smell more MVCish
        vehicleImage = (ImageButton) findViewById(R.id.vehicleImage);
        vehicleName = (EditText) findViewById(R.id.vehicleField);
        vehicleQuantity = (EditText) findViewById(R.id.quantityField);
        vehicleComments = (EditText) findViewById(R.id.commentsField);
        vehiclePublic = (Switch) findViewById(R.id.ispublic);

        done = (Button) findViewById(R.id.button);

        final boolean loadVehicle = getIntent().hasExtra("vehiclePosition");

        if(loadVehicle){
            position = getIntent().getIntExtra("vehiclePosition",0);
            Vehicle loaded = UserSingleton.getCurrentUser().getInventory().getList().get(position);
            //vehicleImage = loaded.getPicture()?
            vehicleName.setText(loaded.getName());
            vehicleQuantity.setText(loaded.getQuantity());
            vehicleComments.setText(loaded.getComments());
            vehiclePublic.setChecked(loaded.getPublic());
            //qualitySpinner.setSelection(loaded.getQuality().)
            
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vehicle vehicle;

                vehicle = new Vehicle();
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
                UserSingleton.getCurrentUser().addItem(vehicle);

                //save the user to ensure all changes are updated
                dm.saveUser(UserSingleton.getCurrentUser());

                Intent intent = new Intent(AddInventoryActivity.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
