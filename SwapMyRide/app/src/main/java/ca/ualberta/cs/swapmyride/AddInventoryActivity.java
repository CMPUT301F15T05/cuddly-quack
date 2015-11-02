package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddInventoryActivity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner categorySpinner;
    Spinner qualitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinventory);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(new ArrayAdapter<VehicleCategory>(this, android.R.layout.simple_spinner_dropdown_item, VehicleCategory.values()));

        qualitySpinner = (Spinner) findViewById(R.id.qualitySpinner);
        qualitySpinner.setAdapter(new ArrayAdapter<VehicleQuality>(this, android.R.layout.simple_spinner_dropdown_item, VehicleQuality.values()));
    }
}
