package com.namclu.android.bloquery.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;

/**
 * Created by namlu on 15-April-17.
 * <p>
 * UserProfileActivity.java presents user with their profile information.
 */
public class UserProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

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
        ImageView imageUserImage = (ImageView) findViewById(R.id.image_user_profile_image);
        TextView textUserEmail = (TextView) findViewById(R.id.text_user_profile_email);

        // Set views
        imageUserImage.setImageResource(R.drawable.common_full_open_on_phone);
        textUserEmail.setText(String.format("%s", userEmail));
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Performing this check is important because if you call startActivityForResult()
        // using an intent that no app can handle, your app will crash
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
