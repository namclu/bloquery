package com.namclu.android.bloquery.api;

import android.util.Log;

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
    public static final String ANSWERS = "answers";

    /* private fields */
    private List<Answer> mAnswers;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAnswerReference;
    private String questionId;

    public AnswerDataSource(String questionId) {
        mAnswers = new ArrayList<>();

        // Data for answers
        writeNewAnswer(questionId, null, "Here is my answer..", 11, 12);
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    // Method to write a new Answer object into database
    private void writeNewAnswer(String questionId, String answerId, String answerString, int numberUpVotes, int numberOfAnswers) {
        Log.v(TAG, "in writeNewAnswer()");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAnswerReference = mDatabaseReference.child(ANSWERS);

        String key = mDatabaseReference.push().getKey();
        Answer answer = new Answer(answerId, answerString, numberUpVotes, numberOfAnswers);
        mAnswerReference.child(key).setValue(answer);
    }
}
