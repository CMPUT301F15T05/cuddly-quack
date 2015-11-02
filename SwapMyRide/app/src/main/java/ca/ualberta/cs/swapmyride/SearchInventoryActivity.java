package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SearchInventoryActivity extends AppCompatActivity {

    Spinner categorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchinventory);

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<VehicleCategory>(this, android.R.layout.simple_spinner_dropdown_item, VehicleCategory.values()));
    }

}
