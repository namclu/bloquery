package com.namclu.android.bloquery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.namclu.android.bloquery.R;

/**
 * Created by namlu on 03-Aug-16.
 */
public class SignupActivity extends AppCompatActivity{

    private static final String TAG = "SignupActivity";

    private EditText _inputName;
    private EditText _inputEmail;
    private EditText _inputPassword;
    private Button _createAccountButton;
    private TextView _loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Views
        _inputName = (EditText) findViewById(R.id.input_name);
        _inputEmail = (EditText) findViewById(R.id.input_email);
        _inputPassword = (EditText) findViewById(R.id.input_password);

        // Buttons
        _createAccountButton = (Button) findViewById(R.id.button_create_account);
        _loginLink = (TextView) findViewById(R.id.link_login_account);

        // Listener for Create Account button
        _createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If successful, create a new user account and log user in
                // Else show an error
                Toast.makeText(SignupActivity.this, "New account pending...", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener for Login link
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Login activity
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
