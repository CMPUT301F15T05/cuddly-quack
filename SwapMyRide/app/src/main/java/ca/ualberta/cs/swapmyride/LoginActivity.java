package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    DataManager dm;
    UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.title_activity_login);

        usernameField = (EditText) findViewById(R.id.usernameField);

        signIn = (Button) findViewById(R.id.signIn);

        signUp = (TextView) findViewById(R.id.signUp);

        dm = new DataManager(LoginActivity.this);
        uController = new UserController();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* username = "adrianomarini"; //usernameField.getText().toString();
                //Log.i("onClick", username);
                Boolean found;
                found = true;//UserController.userExists(username);
                if(found){
                    //UserController.addCurrentUser(username);
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }

                if(!found){
                    Toast.makeText(getApplicationContext(), "Invalid Username!", Toast.LENGTH_LONG).show();
                }*/
                //TODO FIX THIS
                /*User user = new User();
                user.setUserName("hello");
                UserController.addCurrentUser("hello");
                */
                dm.deleteUser("");
                String username = usernameField.getText().toString();

                if(dm.searchUser(username)) {
                    UserSingleton.addCurrentUser(dm.loadUser(username));
                    Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    //TODO TOAST TO TELL USER THAT THE USERNAME DOES NOT EXIST
                    Toast.makeText(LoginActivity.this, username + " Not Found", Toast.LENGTH_LONG).show();
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
