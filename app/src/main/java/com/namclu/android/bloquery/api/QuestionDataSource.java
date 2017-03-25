package com.namclu.android.bloquery.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.api.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 12-Mar-17.
 *
 * QuestionDataSource centralizes control and distribution of the Question data
 */

public class QuestionDataSource {

    /* Constants */
    public static final String TAG = "QuestionDataSource";

    /* private fields */
    private List<Question> mQuestions;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuestionReference;

    public QuestionDataSource() {

    }

    public QuestionDataSource(String questionId) {
        mQuestions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return mQuestions;
    }

    // Method to write a new Question object into database
    public void writeNewQuestion(String questionString, int timeStamp, int numberOfAnswers) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mQuestionReference = mDatabaseReference.child("questions");

        String key = mQuestionReference.push().getKey();
        Question question = new Question(key, questionString, timeStamp, numberOfAnswers);
        mQuestionReference.child(key).setValue(question);
    }
}
