package ca.ualberta.cs.swapmyride;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddFriendProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView fullName;
    TextView email;
    Button addFriend;
    String username;
    DataManager dataManager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addfriendprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        dataManager = new DataManager(getApplicationContext());

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        addFriend = (Button) findViewById(R.id.addFriend);

        username = getIntent().getStringExtra("Username");

        user = dataManager.loadUser(username);

        getSupportActionBar().setTitle(username);
        fullName.setText(user.getName());
        email.setText(user.getUserEmail());

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }

}
