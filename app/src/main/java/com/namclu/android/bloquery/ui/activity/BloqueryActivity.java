package com.namclu.android.bloquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;

/**
 * Created by namlu on 30-Jul-16.
 *
 * BloqueryActivity.java is the default main screen of the app.
 */
public class BloqueryActivity extends Activity{

    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        //FirebaseDatabase.getInstance().getReference("test").push().setValue("Heres a test value");
        test = (TextView) findViewById(R.id.testtext);
    }

    public void get(View view) {

        FirebaseDatabase.getInstance().getReference("test").addChildEventListener(new ChildEventListener() {
            @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                test.append(dataSnapshot.getValue(String.class));
            }

            @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(BloqueryActivity.this, dataSnapshot.getValue(String.class) + " was removed", Toast.LENGTH_LONG).show();
            }

            @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
