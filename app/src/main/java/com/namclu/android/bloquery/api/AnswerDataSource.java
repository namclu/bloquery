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

    /* private fields */
    private List<Answer> mAnswers;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mAnswerReference;
    private String questionId;

    public AnswerDataSource() {

    }
    public AnswerDataSource(String questionId) {
        mAnswers = new ArrayList<>();

        // Data for answers
        writeNewAnswer(questionId, null, "Here is my answer..", 11, 12);
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    // Method to write a new Answer object into database
    public void writeNewAnswer(String questionId, String answerId, String answerString, int numberUpVotes, int timeStamp) {
        //Log.v(TAG, "in writeNewAnswer()");
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mAnswerReference = mDatabaseReference.child("questions").child(questionId).child("answers");

        String key = mAnswerReference.push().getKey();
        Answer answer = new Answer(key, answerString, numberUpVotes, timeStamp);
        mAnswerReference.child(key).setValue(answer);
    }
}
