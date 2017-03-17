package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;

public class SingleQueryActivity extends AppCompatActivity {

    /* Constants */
    public static final String TAG = "SingleQueryActivity";
    public static final String QUESTIONS = "questions";

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

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mQuestionsReference = mDatabaseReference.child(QUESTIONS);

        question = (TextView) findViewById(R.id.text_single_query_question);
        timeStamp = (TextView) findViewById(R.id.text_single_query_time_stamp);
        numAnswers = (TextView) findViewById(R.id.text_single_query_num_answers);
        userImage = (ImageView) findViewById(R.id.image_single_query_user_image);

        question.setText();
    }
}
