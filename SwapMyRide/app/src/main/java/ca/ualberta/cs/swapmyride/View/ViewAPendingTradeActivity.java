package ca.ualberta.cs.swapmyride.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

        position = getIntent().getIntExtra("PendingTradePosition", 0);

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
                tradesController.deletePendingTrade(tradeToDisplay);
                finish();
            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterTradeDialog();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewAPendingTradeActivity.this);
        builder.setMessage("Are you SURE you want to confirm this trade? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                try {
                    tradesController.confirmPendingTrade(tradeToDisplay);
                } catch (Exception e) {
                    notValidTradeDialog();
                }
                finish();
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

    public void notValidTradeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewAPendingTradeActivity.this);
        builder.setMessage("The trade is no longer valid! Do you wish to KEEP the trade for later or DELETE it.");
        builder.setCancelable(false);
        builder.setPositiveButton("Keep", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tradesController.deletePendingTrade(tradeToDisplay);
                finish();
            }
        });
        builder.show();
    }

    public void counterTradeDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewAPendingTradeActivity.this);
        builder.setMessage("Are you SURE you want to counter this trade? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Counter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tradesController.counterPendingTrade(getApplicationContext(), tradeToDisplay);
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tradesController.deletePendingTrade(tradeToDisplay);
                finish();
            }
        });
        builder.show();
    }
}
