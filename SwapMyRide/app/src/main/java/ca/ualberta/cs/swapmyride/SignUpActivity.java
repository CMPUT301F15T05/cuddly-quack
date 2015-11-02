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

        String myName = name.getText().toString();
        String myUsername = username.getText().toString();
        String myEmail = email.getText().toString();
        String myAddress = address.getText().toString();

        final Boolean found = thisSingleton.userExists(myUsername);

        User newUser = new User();
        newUser.setName(myName);
        newUser.setUserName(myUsername);
        newUser.setUserAddress(myAddress);
        newUser.setUserEmail(myEmail);

        Log.i("newUser User", newUser.getName());
        Log.i("newUser UserName", newUser.getUserName());

        thisSingleton.addUser(newUser);


        signUp = (Button) findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(found){
                    Toast.makeText(getApplicationContext(),"Username Exists", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
