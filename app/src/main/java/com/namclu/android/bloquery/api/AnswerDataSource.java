package com.namclu.android.bloquery.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.api.model.Answer;

/**
 * Created by namlu on 22-Mar-17.
 */

public class AnswerDataSource {

    /* Constants */
    public static final String TAG = "AnswerDataSource";

    /* private fields */
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAnswerReference;

    public AnswerDataSource() {

    }
    public AnswerDataSource(String questionId) {

        // Data for answers
        //writeNewAnswer(questionId, null, "Here is my answer..", 11, 12);
    }

    // Method to write a new Answer object into database
    public void writeNewAnswer(String questionId, String answerString, int numberUpVotes, long timeStamp) {
        //Log.v(TAG, "in writeNewAnswer()");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAnswerReference = mDatabaseReference.child("answers").child(questionId);

        String key = mAnswerReference.push().getKey();
        Answer answer = new Answer(key, answerString, numberUpVotes, timeStamp);
        mAnswerReference.child(key).setValue(answer);
    }
}
