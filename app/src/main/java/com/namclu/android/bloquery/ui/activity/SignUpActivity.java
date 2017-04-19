package com.namclu.android.bloquery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.User;

/**
 * Created by namlu on 03-Aug-16.
 * <p>
 * SignUpActivity.java handles the task of signing up and creating a user account for a new user.
 * <p>
 * Before:
 * User provides a Name, Email and a Password
 * After:
 * If successful, a user account will be create and user will be logged in and
 * presented with the Main screen
 * Other:
 * If user already has an account, they can skip to Login screen
 */
public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private EditText mInputName;
    private EditText mInputEmail;
    private EditText mInputPassword;
    private Button mCreateAccountButton;
    private TextView mLoginLink;

    // Firebase objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private DatabaseReference mUsersReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Views
        mInputName = (EditText) findViewById(R.id.input_name);
        mInputEmail = (EditText) findViewById(R.id.input_email);
        mInputPassword = (EditText) findViewById(R.id.input_password);

        // Buttons
        mCreateAccountButton = (Button) findViewById(R.id.button_create_account);
        mLoginLink = (TextView) findViewById(R.id.link_login_account);

        // Initialize Firebase objects
        mAuth = FirebaseAuth.getInstance();
        mUsersReference = FirebaseDatabase.getInstance().getReference("users");

        // Create AuthStateListener to respond to changes in user signin state
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    // Send user to main activity
                    Intent intent = new Intent(SignUpActivity.this, BloqueryActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };

        // Listener for Create Account button
        mCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If successful, create a new user account and log user in
                // Else show an error
                Toast.makeText(SignUpActivity.this, "New account pending...", Toast.LENGTH_SHORT).show();
                createAccount(mInputEmail.getText().toString(), mInputPassword.getText().toString());
            }
        });

        // Listener for Login link
        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the Login activity
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    // Create a new account
    private void createAccount(String email, String password) {

        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // @Todo
        //showProgressDialog();

        // Create user with email and password
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user.
                        // If sign in succeeds the auth state listener will be notified
                        // and logic to handle the signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this,
                                    "Welcome " + mAuth.getCurrentUser().getEmail(),
                                    Toast.LENGTH_SHORT).show();
                            // Create new user
                            User user = new User(false, "", mAuth.getCurrentUser().getEmail());
                            mUsersReference.child(mAuth.getCurrentUser().getUid()).setValue(user);
                        } else {
                            Toast.makeText(SignUpActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // @Todo
                        //hideProgressDialog();
                    }
                });
    }

    // @Todo
    private void updateUI(FirebaseUser user) {
        // @Todo
        //hideProgressDialog();
        if (user != null) {

        } else {

        }
    }

    // Checks if valid data has been given for sign up
    private boolean validateForm() {
        boolean isValid = true;

        // Validate Name, Email, and Password
        String name = mInputName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mInputName.setError(getString(R.string.error_field_required));
            isValid = false;
        } else {
            mInputName.setError(null);
        }

        String email = mInputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mInputEmail.setError(getString(R.string.error_field_required));
            isValid = false;
        } else {
            mInputEmail.setError(null);
        }

        String password = mInputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mInputPassword.setError(getString(R.string.error_field_required));
            isValid = false;
        } else {
            mInputPassword.setError(null);
        }

        return isValid;
    }
}
