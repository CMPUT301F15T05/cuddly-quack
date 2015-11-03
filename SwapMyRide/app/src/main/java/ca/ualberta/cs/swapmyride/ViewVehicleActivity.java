package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ViewVehicleActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView title = (TextView) findViewById(R.id.title);
    TextView quanitiy = (TextView) findViewById(R.id.quality);
    TextView isPublic = (TextView) findViewById(R.id.ispublic);
    TextView quality = (TextView) findViewById(R.id.quantity);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewvehicle);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

    }
}
