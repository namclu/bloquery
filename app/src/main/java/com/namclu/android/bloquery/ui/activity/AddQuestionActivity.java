package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Question;

import static com.namclu.android.bloquery.R.id.button_add_question;

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AddQuestionActivity";

    private EditText mEditAddQuestion;
    private Button mButtonAddQuestion;

    private DatabaseReference mQuestionsReference;

    private FirebaseAuth mCurrentUser = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // Initialise Database
        mQuestionsReference = FirebaseDatabase.getInstance().getReference("questions");

        // Initialise Views
        mEditAddQuestion = (EditText) findViewById(R.id.edit_add_question);
        mButtonAddQuestion = (Button) findViewById(button_add_question);

        mButtonAddQuestion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(mEditAddQuestion.getText())) {
            Toast.makeText(this, "Please enter a question...", Toast.LENGTH_SHORT).show();
        } else {
            String questionString = mEditAddQuestion.getText().toString();

            String key = mQuestionsReference.push().getKey();
            String userId = mCurrentUser.getCurrentUser().getUid();
            Question question = new Question(key, questionString, (long) System.currentTimeMillis(), 0, userId);
            mQuestionsReference.child(key).setValue(question);

            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
