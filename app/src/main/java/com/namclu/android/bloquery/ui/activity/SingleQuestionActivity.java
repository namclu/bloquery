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
import com.namclu.android.bloquery.api.model.Question;

public class SingleQuestionActivity extends AppCompatActivity {

    /* Constants */
    public static final String TAG = "SingleQuestionActivity";

    /* private fields */
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuestionsReference;

    private TextView questionString;
    private TextView timeStamp;
    private TextView numAnswers;
    private ImageView userImage;

    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        // Get the intent data from BloqueryActivity
        String id = getIntent().getStringExtra("ID");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("questions/" + id);

        questionString = (TextView) findViewById(R.id.text_single_question_string);
        timeStamp = (TextView) findViewById(R.id.text_single_question_time_stamp);
        numAnswers = (TextView) findViewById(R.id.text_single_question_num_answers);
        userImage = (ImageView) findViewById(R.id.image_single_question_user_image);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                questionString.setText(dataSnapshot.getValue(Question.class).getQuestionString());
                timeStamp.setText("" + dataSnapshot.getValue(Question.class).getTimeStamp());
                numAnswers.setText("" + dataSnapshot.getValue(Question.class).getNumberOfAnswers());
                //userImage.setImageResource(dataSnapshot.getValue(Question.class).getUserImageResId());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}