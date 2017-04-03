package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Answer;

public class AddAnswerActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "AddAnswerActivity";

    private String mQuestionId;
    private EditText mEditAddAnswer;
    private Button mButtonAddAnswer;

    private DatabaseReference mAnswerReference;

    private FirebaseAuth mCurrentUser = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_answer);

        // Get question key from intent
        mQuestionId = getIntent().getStringExtra("question_id_key");

        // Initialise Database
        mAnswerReference = FirebaseDatabase.getInstance().getReference().
                child("answers").child(mQuestionId);

        // Initialise Views
        mEditAddAnswer = (EditText) findViewById(R.id.edit_add_answer);
        mButtonAddAnswer = (Button) findViewById(R.id.button_add_answer);

        mButtonAddAnswer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String answerString = mEditAddAnswer.getText().toString();

        String key = mAnswerReference.push().getKey();
        String userId = mCurrentUser.getCurrentUser().getUid();
        Answer answer = new Answer(userId, key, answerString, (long) System.currentTimeMillis(), 0);
        mAnswerReference.child(key).setValue(answer);

        Toast.makeText(this, "Answer Added!", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
