package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.FeedAdapter;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.R;

public class ViewAPendingTradeActivity extends AppCompatActivity {

    // TODO add things here
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpendingtrade);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        ListView friendInventory = (ListView) findViewById(R.id.friendInventory);
        ListView userInventory = (ListView) findViewById(R.id.userInventory);

        int position = getIntent().getIntExtra("PastTradePosition", 0);

        ArrayList<Trade> trades = UserSingleton.getCurrentUser().getPendingTrades().getTrades();
        Trade tradeToDisplay = trades.get(position);

        FeedAdapter adapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getBorrowerItems());
        FeedAdapter userAdapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getOwnerItems());

        friendInventory.setAdapter(adapter);
        userInventory.setAdapter(userAdapter);

    }
}
