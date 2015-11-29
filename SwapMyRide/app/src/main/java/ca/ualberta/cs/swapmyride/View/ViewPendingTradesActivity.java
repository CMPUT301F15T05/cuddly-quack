package ca.ualberta.cs.swapmyride.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.TradeList;
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

        pendingTrades = (ListView)findViewById(R.id.pendingTrades);

        ArrayList<Trade> tradeList = UserSingleton.getCurrentUser().getPendingTrades().getTrades();

        for (Trade trade: tradeList) {
            String s = String.format("%s wants to trade %d item(s) for your %d item(s)", trade.getOwner(), trade.getOwnerItems().size(), trade.getBorrowerItems().size());

            pendingTradeArray.add(s);
        }

        pendingTrades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewAPendingTradeActivity.class);

                intent.putExtra("PendingTradePosition", position);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        pendingTradeArray = new ArrayList<>();

        ArrayList<Trade> tradeList = UserSingleton.getCurrentUser().getPendingTrades().getTrades();

        for (Trade trade: tradeList) {
            String s = String.format("%s wants to trade %d item(s) for your %d item(s)", trade.getOwner(), trade.getOwnerItems().size(), trade.getBorrowerItems().size());

            pendingTradeArray.add(s);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pendingTradeArray);

        pendingTrades.setAdapter(adapter);
    }
}
