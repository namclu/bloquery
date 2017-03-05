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
import com.namclu.android.bloquery.R;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * LoginActivity.java handles logging in a user who already has an account.
 * <p>
 * Before:
 * User provides their account Email and Password to login
 * After:
 * If successful, user is logged into their account and is present with Main screen
 * Other:
 * If user doesn't have an account, they can be taken to SignUp screen
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private static final int REQUEST_SIGNUP = 0;

    private EditText mInputEmail;
    private EditText mInputPassword;
    private Button mLoginButton;
    private TextView mCreateAccountLink;

    // Create reference to Firebase mAuth
    private FirebaseAuth mAuth;

    // Create reference to mAuthStateListener
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Views
        mInputEmail = (EditText) findViewById(R.id.input_email);
        mInputPassword = (EditText) findViewById(R.id.input_password);

        // Buttons
        mLoginButton = (Button) findViewById(R.id.button_login);
        mCreateAccountLink = (TextView) findViewById(R.id.link_create_account);

        // Initialize Firebase mAuth object
        mAuth = FirebaseAuth.getInstance();

        // Create AuthStateListener to respond to changes in user signin state
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    // Send user to main activity
                    Intent intent = new Intent(LoginActivity.this, BloqueryActivity.class);
                    startActivity(intent);

                    // Call to destroy an activity
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                updateUI(user);
            }
        };

        // Listener for Login button
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If successful, take user to initial screen.
                // Otherwise show an error
                signIn(mInputEmail.getText().toString(), mInputPassword.getText().toString());
            }
        });

        // Listener for Create Account
        mCreateAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the signup activity
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
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

    // Sign in user
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        // @Todo
        //showProgressDialog();

        // Sign in user with email and password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user.
                        // If sign in succeeds the auth state listener will be notified
                        // and logic to handle the signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Welcome back " + mAuth.getCurrentUser().getEmail(),
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

    // @Todo
    private boolean validateForm() {
        boolean valid = true;

        // Validate Email, and Password
        String email = mInputEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mInputEmail.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mInputEmail.setError(null);
        }

        String password = mInputPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mInputPassword.setError(getString(R.string.error_field_required));
            valid = false;
        } else {
            mInputPassword.setError(null);
        }

        return valid;
    }
}
