package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewFriendsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView friendView;
    FriendAdapter adapter;
    ArrayList<User> friendsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewfriends);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        friendView = (ListView) findViewById(R.id.friendView);

        friendsList = UserSingleton.getCurrentUser().getFriends().getFriendList();

        adapter = new FriendAdapter(getApplicationContext(), friendsList);

        friendView.setAdapter(adapter);

        friendView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), ViewFriendProfileActivity.class);

                //add the vehicle that has been selected to the intent to pass
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
