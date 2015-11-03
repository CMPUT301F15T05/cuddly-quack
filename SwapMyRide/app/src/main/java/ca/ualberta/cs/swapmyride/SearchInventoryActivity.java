package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class SearchInventoryActivity extends AppCompatActivity {

    EditText searchField;
    Spinner categorySpinner;
    Button inventorySearch;
    VehicleCategory vehicleCategory;
    ArrayList<Vehicle> inventoryList;
    SearchController searchController;
    ArrayList<Vehicle> foundVehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchinventory);

        inventoryList = UserSingleton.getCurrentUser().getInventory().getList();

        searchField = (EditText) findViewById(R.id.searchField);
        inventorySearch = (Button) findViewById(R.id.inventorySearch);
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
                vehicleCategory = VehicleCategory.NONE;
            }
        });

        inventorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foundVehicles = searchController.findInventoryVehicle(searchField.getText().toString(), vehicleCategory, inventoryList);
            }
        });

    }

}
