package ca.ualberta.cs.swapmyride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    UserSingleton userSingleton;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        user = userSingleton.getCurrentUser();

        TextView usernameTextView = (TextView) findViewById(R.id.usernameField);
        usernameTextView.setText(user.getUserName().toString());

        usernameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditProfileActivity.this, "Cannot edit username", Toast.LENGTH_SHORT).show();
            }
        });

        TextView nameTextView = (TextView) findViewById(R.id.nameField);
        nameTextView.setText(user.getName());

        TextView emailTextView = (TextView) findViewById(R.id.emailField);
        emailTextView.setText(user.getUserEmail());

        TextView addressTextView = (TextView) findViewById(R.id.editText2);
        addressTextView.setText(user.getUserAddress());

    }


}
