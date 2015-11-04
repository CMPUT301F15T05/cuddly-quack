package ca.ualberta.cs.swapmyride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewVehicleActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView title;
    TextView quanitiy;
    TextView category;
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
        category = (TextView) findViewById(R.id.category);
        quality = (TextView) findViewById(R.id.quantity);
        comments = (TextView) findViewById(R.id.commentsHead);

        delete = (Button) findViewById(R.id.delete);
        editVehicle = (Button) findViewById(R.id.edit);
        done = (Button) findViewById(R.id.done);

        initOnClickListeners();

        Vehicle vehicle;
        int position = getIntent().getIntExtra("vehiclePosition", 0);

        vehicle = UserSingleton.getCurrentUser().getInventory().getList().get(position);

        title.setText(vehicle.getName());
        quanitiy.setText(vehicle.getQuantity().toString());
        category.setText(vehicle.getCategory().getCategory());
        quality.setText(vehicle.getQuality().getQuality());
        comments.setText(vehicle.getComments());


    }

    public void initOnClickListeners(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });
    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewVehicleActivity.this);
        builder.setMessage("Are you SURE you want to delete this vehicle? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //TODO DELETE THE VEHICLE
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
