package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.FeedAdapter;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.R;

/**
 * The view that displays already completed trades
 */
public class ViewAPastTradeActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView friendInventory;
    ListView userInventory;
    int position;
    ArrayList<Trade> trades;
    Trade tradeToDisplay;
    FeedAdapter friendAdapter;
    FeedAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpasttrade);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        friendInventory = (ListView) findViewById(R.id.friendInventory);
        userInventory = (ListView) findViewById(R.id.userInventory);

        position = getIntent().getIntExtra("PastTradePosition", 0);

        trades = UserSingleton.getCurrentUser().getPastTrades().getTrades();
        tradeToDisplay = trades.get(position);

        friendAdapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getBorrowerItems());
        userAdapter = new FeedAdapter(getApplicationContext(), tradeToDisplay.getOwnerItems());

        friendInventory.setAdapter(friendAdapter);
        userInventory.setAdapter(userAdapter);
    }

}
