package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.AnswerDataSource;

public class SubmitAnswerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SubmitAnswerActivity";

    private String mQuestionId;
    private AnswerDataSource mAnswerDataSource;
    private EditText mEditSubmitAnswer;
    private Button mButtonSubmitAnswer;

    private DatabaseReference mAnswerReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

        // Get question key from intent
        mQuestionId = getIntent().getStringExtra("question_id");

        // Initialise Database
        mAnswerReference = FirebaseDatabase.getInstance().getReference().
                child("questions").child(mQuestionId).child("answers");

        // Initialise Views
        mEditSubmitAnswer = (EditText) findViewById(R.id.edit_submit_answer);
        mButtonSubmitAnswer = (Button) findViewById(R.id.button_submit_answer);

        mButtonSubmitAnswer.setOnClickListener(this);
        mAnswerDataSource = new AnswerDataSource();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Add value event listener to the post
        /*ValueEventListener answerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (mEditSubmitAnswer != null) {
                    String answer = mEditSubmitAnswer.getText().toString();
                    //String answerKey = mAnswerReference.push();

                    Toast.makeText(SubmitAnswerActivity.this, "Answer Added!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mAnswerReference.addValueEventListener(answerListener);*/
    }

    @Override
    public void onClick(View view) {
        String answerString = mEditSubmitAnswer.getText().toString();
        mAnswerDataSource.writeNewAnswer(mQuestionId, null, answerString, 0, 0);
        Toast.makeText(this, "Answer Added!", Toast.LENGTH_SHORT).show();
        this.finish();
        /*if (mEditSubmitAnswer != null) {
            String answer = mEditSubmitAnswer.getText().toString();
            mAnswerReference.setValue(answer);

            Toast.makeText(SubmitAnswerActivity.this, "Answer Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SubmitAnswerActivity.this, "Please enter an answer.", Toast.LENGTH_SHORT).show();
        }*/
    }
}
