package ca.ualberta.cs.swapmyride.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.R;

public class ViewPendingTradesActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView pendingTrades;
    ArrayList<String> pendingTradeArray;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pendingtrades);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        pendingTradeArray = new ArrayList<>();

        pendingTrades = (ListView)findViewById(R.id.pendingTrades);

        ArrayList<Trade> tradeList = UserSingleton.getCurrentUser().getPendingTrades().getTrades();

        for (Trade trade: tradeList) {
            String s = String.format("%s wants to trade %d item(s) for your %d item(s)", trade.getBorrower(), trade.getBorrowerItems().size(), trade.getOwnerItems().size());

            pendingTradeArray.add(s);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingTradeArray);

        pendingTrades.setAdapter(adapter);
    }

}
