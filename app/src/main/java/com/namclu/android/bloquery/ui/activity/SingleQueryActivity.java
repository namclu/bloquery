package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Query;

public class SingleQueryActivity extends AppCompatActivity {

    /* Constants */
    public static final String TAG = "SingleQueryActivity";

    /* private fields */
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuestionsReference;

    private TextView question;
    private TextView timeStamp;
    private TextView numAnswers;
    private ImageView userImage;

    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_query);

        // Get the intent data from BloqueryActivity
        String id = getIntent().getStringExtra("ID");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("questions/" + id);

        question = (TextView) findViewById(R.id.text_single_query_question);
        timeStamp = (TextView) findViewById(R.id.text_single_query_time_stamp);
        numAnswers = (TextView) findViewById(R.id.text_single_query_num_answers);
        userImage = (ImageView) findViewById(R.id.image_single_query_user_image);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                question.setText(dataSnapshot.getValue(Query.class).getQuestion());
                timeStamp.setText("" + dataSnapshot.getValue(Query.class).getTimeStamp());
                numAnswers.setText("" + dataSnapshot.getValue(Query.class).getNumberOfAnswers());
                //userImage.setImageResource(dataSnapshot.getValue(Query.class).getUserImageResId());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
