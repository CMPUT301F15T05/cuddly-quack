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
import ca.ualberta.cs.swapmyride.R;

public class ViewPastTradesActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView pastTrades;
    ArrayList<String> pastTradeArray;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpasttrades);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        pastTrades = (ListView) findViewById(R.id.pastTrades);

        pastTrades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ViewAPastTradeActivity.class);
                intent.putExtra("PastTradePosition", position);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        pastTradeArray = new ArrayList<>();

        ArrayList<Trade> tradeList = UserSingleton.getCurrentUser().getPastTrades().getTrades();

        for (Trade trade: tradeList) {
            String s = String.format("%s traded %d item(s) for %d item(s)", trade.getOwner(), trade.getOwnerItems().size(), trade.getBorrowerItems().size());
            pastTradeArray.add(s);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pastTradeArray);

        pastTrades.setAdapter(adapter);
    }

}
