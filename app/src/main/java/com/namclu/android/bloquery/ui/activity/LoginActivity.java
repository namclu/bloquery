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
 * Created by namlu on 30-Jul-16.
 */
public class LoginActivity extends AppCompatActivity{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private EditText _inputEmail;
    private EditText _inputPassword;
    private TextView _createAccountLink;
    private Button _loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        _inputEmail = (EditText) findViewById(R.id.input_email);
        _inputPassword = (EditText) findViewById(R.id.input_password);
        _createAccountLink = (TextView) findViewById(R.id.link_create_account);

        // Buttons
        _loginButton = (Button) findViewById(R.id.button_login);

        // Listener for Login button
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If successful, take user to initial screen.
                // Otherwise show an error
                Intent intent = new Intent(LoginActivity.this, BloQueryActivity.class);
                startActivity(intent);
            }
        });

        // Listener for Create Account
        _createAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the create account activity
                Toast.makeText(LoginActivity.this, "New account pending...", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
