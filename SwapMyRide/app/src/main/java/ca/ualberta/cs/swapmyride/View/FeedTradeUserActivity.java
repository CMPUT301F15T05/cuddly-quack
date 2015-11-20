package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.InventoryList;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
import ca.ualberta.cs.swapmyride.R;

public class FeedTradeUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView feedMultipleView;
    Button trade;
    InventoryList userInventory;
    ArrayList<String> vehicleNames;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedtradeuser);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(UserSingleton.getCurrentUser().getUserName()+" Inventory");

        userInventory = UserSingleton.getCurrentUser().getInventory();

        trade = (Button) findViewById(R.id.trade);
        feedMultipleView = (ListView) findViewById(R.id.feedMultipleView);

        vehicleNames = new ArrayList<>();

        for (Vehicle vehicle: userInventory.getList()) {
            vehicleNames.add(vehicle.getName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, vehicleNames);
        feedMultipleView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        feedMultipleView.setAdapter(adapter);

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            // http://theopentutorials.com/tutorials/android/listview/android-multiple-selection-listview/#ListViewMultipleSelectionActivityjava
            public void onClick(View v) {
                // Clearing the UserSingleton's currentTrade's borrowers list
                UserSingleton.currentTrade.clearOwnerItems();

                SparseBooleanArray checked = feedMultipleView.getCheckedItemPositions();
                for (int i = 0; i < checked.size(); i++) {
                    // Item position in adapter
                    int position = checked.keyAt(i);

                    if (checked.valueAt(i)) {
                        // Adding items of friendInventory that are checked
                        UserSingleton.currentTrade.addOwnerItem(userInventory.getList().get(position));
                    }
                }
                finish();
            }
        });
    }
}
