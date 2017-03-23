package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.AnswerDataSource;

public class SubmitAnswerActivity extends AppCompatActivity {

    private String mQuestionId;
    private AnswerDataSource mAnswerDataSource;
    private EditText mEditSubmitAnswer;
    private Button mButtonSubmitAnswer;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

        mQuestionId = getIntent().getStringExtra("question_id");

        mEditSubmitAnswer = (EditText) findViewById(R.id.edit_submit_answer);
        mButtonSubmitAnswer = (Button) findViewById(R.id.button_submit_answer);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(
                "questions/" + mQuestionId + "/answers");

        mButtonSubmitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditSubmitAnswer != null) {
                    String answer = mEditSubmitAnswer.getText().toString();
                    mDatabaseReference.setValue(answer);

                    Toast.makeText(SubmitAnswerActivity.this, "Answer Added!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SubmitAnswerActivity.this, "Please enter an answer.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        ValueEventListener answerListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
