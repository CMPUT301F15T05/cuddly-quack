package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;


public class ViewFeedInventory extends AppCompatActivity {

    Toolbar toolbar;
    Button trade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedinventory);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        trade = (Button) findViewById(R.id.trade);
        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewFeedInventory.this, FeedTradeActivity.class);
                startActivity(intent);
            }
        });

    }

}
