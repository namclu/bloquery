package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.namclu.android.bloquery.R;

/**
 * Created by namlu on 15-April-17.
 * <p>
 * UserProfileActivity.java presents user with their profile information.
 */
public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Add Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_user_profile);
        setSupportActionBar(toolbar);

        // Get the intent data
        String userEmail = getIntent().getStringExtra("current_user_email");

        // Initialise views
        TextView textUserEmail = (TextView) findViewById(R.id.text_user_profile_email);

        // Set text
        textUserEmail.setText(String.format("%s", userEmail));
    }
}
