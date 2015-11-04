/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    DataManager dm;
    UserController uController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupscreen);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.nameField);
        username = (EditText) findViewById(R.id.usernameField);
        email = (EditText) findViewById(R.id.emailField);
        address = (EditText) findViewById(R.id.editText2);
        dm = new DataManager(SignUpActivity.this);
        uController = new UserController();

        signUp = (Button) findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myName = name.getText().toString();
                String myUsername = username.getText().toString();
                String myEmail = email.getText().toString();
                String myAddress = address.getText().toString();

                //check the username does not already exist
                if(dm.searchUser(myUsername)){
                    Toast.makeText(getApplicationContext(),"Username Already Exists", Toast.LENGTH_LONG).show();
                    username.setText("");
                }

                else if(!dm.searchUser(myUsername) && !myUsername.equals("")){
                    User newUser = new User();
                    newUser.setName(myName);
                    newUser.setUserName(myUsername);
                    newUser.setUserAddress(myAddress);
                    newUser.setUserEmail(myEmail);

                    //set the current user to this new user
                    UserSingleton.addCurrentUser(newUser);

                    //save the user into file so we can access it again later
                    dm.saveUser(newUser);

                    //launch back to login screen
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                //Do not allow a blank username
                else if(myUsername.equals("")){
                    Toast.makeText(SignUpActivity.this, "Username Cannot Be Blank", Toast.LENGTH_LONG).show();
                    username.setText("");
                }
            }
        });
    }

}
