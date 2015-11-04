package ca.ualberta.cs.swapmyride;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchFriendsActivity extends AppCompatActivity {

    SearchController searchController;

    Toolbar toolbar;
    EditText searchField;
    ImageButton searchButton;
    ListView friendView;
    ArrayList<User> foundUsers;
    FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfriends);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        searchController = new SearchController();

        foundUsers = new ArrayList<User>();

        searchField = (EditText) findViewById(R.id.searchField);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        friendView = (ListView) findViewById(R.id.friendView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foundUsers = searchController.findUser(searchField.getText().toString(), getApplicationContext());

                adapter = new FriendAdapter(getApplicationContext(), foundUsers);

                friendView.setAdapter(adapter);

            }
        });
    }

}
