package ca.ualberta.cs.swapmyride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewFriendProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    DataManager dataManager;
    User myself;
    User friend;
    FriendsList friendsList;
    TextView fullName;
    TextView email;
    TextView address;
    Button removeFriend;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfriendprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        dataManager = new DataManager(getApplicationContext());

        fullName = (TextView) findViewById(R.id.fullName);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);
        removeFriend = (Button) findViewById(R.id.removeFriend);

        position = getIntent().getIntExtra("Position",0);

        myself = UserSingleton.getCurrentUser();

        friendsList = myself.getFriends();

        friend = friendsList.getFriendList().get(position);

        getSupportActionBar().setTitle(friend.getUserName());
        fullName.setText(friend.getName());
        email.setText(friend.getUserEmail());
        address.setText(friend.getUserAddress());

        removeFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog();
            }
        });

    }

    public void deleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewFriendProfileActivity.this);
        builder.setMessage("Are you SURE you want to remove this friend? It is a permanent Action!");
        builder.setCancelable(false);
        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                myself.getFriends().removeFriend(friend);
                dataManager.saveUser(myself);
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
