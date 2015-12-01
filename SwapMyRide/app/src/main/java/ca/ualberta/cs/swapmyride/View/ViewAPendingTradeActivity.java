package ca.ualberta.cs.swapmyride.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ca.ualberta.cs.swapmyride.Adapter.FeedAdapter;
import ca.ualberta.cs.swapmyride.Controller.LocalDataManager;
import ca.ualberta.cs.swapmyride.Controller.TradesController;
import ca.ualberta.cs.swapmyride.Misc.InvalidTradeException;
import ca.ualberta.cs.swapmyride.Misc.UserSingleton;
import ca.ualberta.cs.swapmyride.Model.Trade;
import ca.ualberta.cs.swapmyride.Model.Vehicle;
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
    Button returned;

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
        returned = (Button) findViewById(R.id.buttonReturned);
        returned.setVisibility(View.INVISIBLE);

        position = getIntent().getIntExtra("PendingTradePosition", 0);

        trades = UserSingleton.getCurrentUser().getPendingTrades().getTrades();
        tradeToDisplay = trades.get(position);

        if (tradeToDisplay.getOwner().equals(UserSingleton.getCurrentUser().getUserName())) {
            counter.setVisibility(View.INVISIBLE);
            confirm.setVisibility(View.INVISIBLE);
            if(tradeToDisplay.getIsAccepted() && tradeToDisplay.getIsBorrowing()){
                delete.setVisibility(View.VISIBLE);
                returned.setVisibility(View.VISIBLE);
            }
        } else {
            if(tradeToDisplay.getIsAccepted() && tradeToDisplay.getIsBorrowing()) {
                counter.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.INVISIBLE);
                delete.setVisibility(View.INVISIBLE);
            }
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

        returned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnedDialog();
            }
        });
    }

    public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewAPendingTradeActivity.this);
        builder.setMessage("Are you SURE you want to confirm this trade? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                try {
                    tradesController.confirmPendingTrade(tradeToDisplay);

                    //Portions of this page are modifications based on work created and
                    // shared by the Android Open Source Project and used according to
                    // terms described in the Creative Commons 2.5 Attribution License.
                    //Accessed 2015-11-29
                    //http://developer.android.com/guide/components/intents-common.html#Email
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("The following trade has been confirmed - ");

                    stringBuilder.append(tradeToDisplay.getOwner() + " has traded: ");
                    for (Vehicle item : tradeToDisplay.getOwnerItems()) {
                        stringBuilder.append(item.getName());
                    }

                    stringBuilder.append(tradeToDisplay.getBorrower() + " has traded: ");
                    for (Vehicle item : tradeToDisplay.getBorrowerItems()) {
                        stringBuilder.append(item.getName());
                    }


                    String body = stringBuilder.toString();

                    Intent sender = new Intent(Intent.ACTION_SENDTO);
                    sender.setType("*/*");
                    sender.setData(Uri.parse("mailto:"));
                    String ownerEmail = new LocalDataManager(getApplicationContext()).loadUser(tradeToDisplay.getOwner()).getUserEmail();
                    String borrowerEmail = new LocalDataManager(getApplicationContext()).loadUser(tradeToDisplay.getBorrower()).getUserEmail();
                    sender.putExtra(Intent.EXTRA_EMAIL, new String[]{ownerEmail, borrowerEmail});
                    sender.putExtra(Intent.EXTRA_SUBJECT, "[SwapMyRide] Trade Complete");
                    sender.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(sender, "email"));
                } catch (InvalidTradeException e) {
                    notValidTradeDialog();
                }
                Intent intent = new Intent(ViewAPendingTradeActivity.this, MainMenu.class);
                startActivity(intent);
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

    public void notValidTradeDialog() {
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

    public void counterTradeDialog() {
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
            }
        });
        builder.show();
    }

    public void returnedDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewAPendingTradeActivity.this);
        builder.setMessage("Are you SURE you want to return this trade? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                try {
                    tradesController.returnPendingTrade(tradeToDisplay);
                } catch (InvalidTradeException e) {
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
}
