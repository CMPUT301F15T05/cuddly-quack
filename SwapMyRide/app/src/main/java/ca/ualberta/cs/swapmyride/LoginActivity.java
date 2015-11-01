package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText usernameField;
    Button signIn;
    TextView signUp;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        final UserSingleton thisSingleton = UserSingleton.getInstance();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_activity_login);

        usernameField = (EditText) findViewById(R.id.usernameField);
        username = usernameField.getText().toString();

        signIn = (Button) findViewById(R.id.signIn);

        signUp = (TextView) findViewById(R.id.signUp);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean found = thisSingleton.userExists(username);
                if(found){
                    thisSingleton.addCurrentUser(username);
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }

                if(!found){
                    Toast.makeText(getApplicationContext(), "Username does not Exist, Please Sign Up!", Toast.LENGTH_LONG).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
