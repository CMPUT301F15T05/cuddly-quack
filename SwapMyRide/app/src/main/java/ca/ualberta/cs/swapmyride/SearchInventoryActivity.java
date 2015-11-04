package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;


public class SearchInventoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText searchField;
    Spinner categorySpinner;
    Button inventorySearch;
    ListView searchList;
    VehicleCategory vehicleCategory;
    ArrayList<Vehicle> inventoryList;
    SearchController searchController;
    ArrayList<Vehicle> foundVehicles;
    InventoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchinventory);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        searchController = new SearchController();

        searchList = (ListView) findViewById(R.id.searchList);

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

        // TODO setonclicklistners to get the item description
        inventorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foundVehicles = searchController.findInventoryVehicle(searchField.getText().toString(), vehicleCategory, inventoryList);

                adapter = new InventoryAdapter(getApplicationContext(), foundVehicles);

                searchList.setAdapter(adapter);
            }
        });

    }

}
