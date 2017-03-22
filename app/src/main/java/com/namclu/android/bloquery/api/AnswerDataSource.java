package com.namclu.android.bloquery.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.api.model.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 22-Mar-17.
 */

public class AnswerDataSource {

    /* Constants */
    public static final String TAG = "AnswerDataSource";
    public static final String ANSWERS = "/questions/" + questionId + "/answers";

    /* private fields */
    private List<Answer> mAnswers;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAnswerReference;

    public AnswerDataSource() {
        mAnswers = new ArrayList<>();
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    // Method to write a new Answer object into database
    private void writeNewAnswer(String questionString, int timeStamp, int numberOfAnswers) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAnswerReference = mDatabaseReference.child(ANSWERS);

        String key = mDatabaseReference.push().getKey();
        Answer answer = new Answer(key, questionString, timeStamp, numberOfAnswers);
        mAnswerReference.child(key).setValue(answer);
    }
}
