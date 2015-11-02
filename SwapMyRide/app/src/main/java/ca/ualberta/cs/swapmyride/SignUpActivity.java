package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button signUp;
    EditText name;
    EditText username;
    EditText email;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupscreen);
        UserSingleton thisSingleton = UserSingleton.getInstance();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.nameField);
        username = (EditText) findViewById(R.id.usernameField);
        email = (EditText) findViewById(R.id.emailField);
        address = (EditText) findViewById(R.id.editText2);

        final String myName = name.getText().toString();
        final String myUsername = username.getText().toString();
        final String myEmail = email.getText().toString();
        final String myAddress = address.getText().toString();

        final Boolean found = UserController.userExists(myUsername);

        signUp = (Button) findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(found){
                    Toast.makeText(getApplicationContext(),"Username Exists", Toast.LENGTH_LONG).show();
                }

                if(!found){
                    User newUser = new User();
                    newUser.setUserName(myName);
                    newUser.setUser(myUsername);
                    newUser.setUserAddress(myAddress);
                    newUser.setUserEmail(myEmail);
                    UserController.addUser(newUser);
                }
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
