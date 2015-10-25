package ca.ualberta.cs.swapmyride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText usernameField;
    EditText passwordField;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        usernameField = (EditText) findViewById(R.id.usernameField);

        passwordField = (EditText) findViewById(R.id.passwordField);

        signIn = (Button) findViewById(R.id.signIn);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

    }



}
