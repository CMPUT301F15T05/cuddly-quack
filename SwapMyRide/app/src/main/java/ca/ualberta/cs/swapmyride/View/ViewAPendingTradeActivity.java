package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.FeedAdapter;
import ca.ualberta.cs.swapmyride.Controller.TradesController;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.R;

public class ViewAPendingTradeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView friendInventory;
    ListView userInventory;
    Button delete;
    Button counter;
    Button confirm;
    int position;
    ArrayList<Trade> trades;
    Trade tradeToDisplay;
    FeedAdapter friendAdapter;
    FeedAdapter userAdapter;
    TradesController tradesController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpendingtrade);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        tradesController = new TradesController(getApplicationContext());

        friendInventory = (ListView) findViewById(R.id.friendInventory);
        userInventory = (ListView) findViewById(R.id.userInventory);

        delete = (Button) findViewById(R.id.delete);
        counter = (Button) findViewById(R.id.counter);
        confirm = (Button) findViewById(R.id.confirm);

        position = getIntent().getIntExtra("PastTradePosition", 0);

        trades = UserSingleton.getCurrentUser().getPendingTrades().getTrades();
        tradeToDisplay = trades.get(position);

        if (tradeToDisplay.getOwner().equals(UserSingleton.getCurrentUser().getUserName())) {
            counter.setVisibility(View.INVISIBLE);
            confirm.setVisibility(View.INVISIBLE);
        }

        friendAdapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getBorrowerItems());
        userAdapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getOwnerItems());

        friendInventory.setAdapter(friendAdapter);
        userInventory.setAdapter(userAdapter);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tradesController.removePendingTrades(tradeToDisplay);
                finish();
            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add this
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add this
            }
        });
    }
}
