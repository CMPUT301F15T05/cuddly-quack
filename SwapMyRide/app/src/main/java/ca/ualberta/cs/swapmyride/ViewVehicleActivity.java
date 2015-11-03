package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class ViewVehicleActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    TextView quanitiy;
    TextView isPublic;
    TextView quality;
    TextView comments;
    Button delete;
    Button editVehicle;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewvehicle);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.title);
        quanitiy = (TextView) findViewById(R.id.quality);
        isPublic = (TextView) findViewById(R.id.ispublic);
        quality = (TextView) findViewById(R.id.quantity);
        comments = (TextView) findViewById(R.id.commentsHead);
        delete = (Button) findViewById(R.id.delete);
        editVehicle = (Button) findViewById(R.id.edit);
        done = (Button) findViewById(R.id.done);



    }
}
