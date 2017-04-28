package com.namclu.android.bloquery.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by namlu on 15-April-17.
 * <p>
 * UserProfileActivity.java presents user with their profile information.
 */
public class UserProfileActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;

    private ImageView mImageUserImage;
    private TextView mTextUserEmail;
    private Button mButtonTakePhoto;
    private String mCurrentPhotoPath;

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
        mImageUserImage = (ImageView) findViewById(R.id.image_user_profile_image);
        mTextUserEmail = (TextView) findViewById(R.id.text_user_profile_email);
        mButtonTakePhoto = (Button) findViewById(R.id.button_user_profile_take_photo);

        // Set views
        mImageUserImage.setImageResource(R.drawable.common_full_open_on_phone);
        mTextUserEmail.setText(String.format("%s", userEmail));

        mButtonTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageUserImage.setImageBitmap(imageBitmap);
        }
    }

    // Invokes an Intent to capture a photo
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Performing this check is important because if you call startActivityForResult()
        // using an intent that no app can handle, your app will crash
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create file where photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating file
            }

            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(
                        this, "com.namclu.android.bloquery", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create image file name
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
