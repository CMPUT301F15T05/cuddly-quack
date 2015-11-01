package ca.ualberta.cs.swapmyride;

/**
 * Created by Daniel on 2015-10-24.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Tab2 extends Fragment {

    ListView inventory;
    ArrayList<Vehicle> arrayOfVehicle;
    InventoryList inventoryList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);

        inventoryList = new InventoryList();

        // Testing
        Vehicle vehicle = new Vehicle();
        vehicle.setName("The Very Very Very Very Cool Car");
        vehicle.setPublic(true);
        vehicle.setQuantity(2);
        inventoryList.add(vehicle);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setName("The Very Very Very Very Cool Car");
        vehicle2.setPublic(true);
        vehicle2.setQuantity(2);
        inventoryList.add(vehicle2);


        Vehicle vehicle3 = new Vehicle();
        vehicle3.setName("The Very Very Very Very Cool Car");
        vehicle3.setPublic(true);
        vehicle3.setQuantity(2);
        inventoryList.add(vehicle3);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setName("The Very Very Very Very Cool Car");
        vehicle4.setPublic(true);
        vehicle4.setQuantity(2);
        inventoryList.add(vehicle4);

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setName("The Very Very Very Very Cool Car");
        vehicle5.setPublic(true);
        vehicle5.setQuantity(2);
        inventoryList.add(vehicle5);
        // End Testing

        arrayOfVehicle = inventoryList.getList();

        InventoryAdapter adapter = new InventoryAdapter(getActivity(), arrayOfVehicle);

        inventory = (ListView) v.findViewById(R.id.inventoryView);

        inventory.setAdapter(adapter);

        // TODO Pass state to ViewVehicleActivity!
        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(getActivity(),ViewVehicleActivity.class);
               startActivity(intent);
            }
        });

        return v;
    }
}