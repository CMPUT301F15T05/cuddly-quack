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
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Tab2 extends Fragment {

    Button addInventory;
    ListView inventory;
    ArrayList<Vehicle> arrayOfVehicle;
    InventoryList inventoryList;
    InventoryAdapter adapter;
    Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);

        addInventory = (Button) v.findViewById(R.id.addInventory);

        inventoryList = UserSingleton.getCurrentUser().getInventory();

        arrayOfVehicle = inventoryList.getList();

        adapter = new InventoryAdapter(getActivity(), arrayOfVehicle);

        inventory = (ListView) v.findViewById(R.id.inventoryView);

        inventory.setAdapter(adapter);

        // TODO Pass state to ViewVehicleActivity!
        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),ViewVehicleActivity.class);

                //add the vehicle that has been selected to the intent to pass
                intent.putExtra("vehiclePosition", position);
                startActivity(intent);
            }
        });

        addInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddInventoryActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}