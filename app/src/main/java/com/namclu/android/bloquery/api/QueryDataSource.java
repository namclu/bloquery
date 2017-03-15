package com.namclu.android.bloquery.api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.api.model.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 12-Mar-17.
 *
 * QueryDataSource centralizes control and distribution of the Query data
 */

public class QueryDataSource {

    public static final String TAG = "QueryDataSource";
    public static final String QUESTIONS = "questions";

    private List<Query> mQueries;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuestionsReference;

    public QueryDataSource() {
        mQueries = new ArrayList<>();

        writeNewQuery("What is...?", 11, 7);
        writeNewQuery("How to ask...?", 9, 34);

        // Add elements to Query ArrayList
        /*mQueries.add(new Query("What is the square root of a pickle?", 7));
        mQueries.add(new Query("What is the square root of an orange?", 3));
        mQueries.add(new Query("What is the square root of a root?", 17));*/
    }

    public List<Query> getQueries() {
        return mQueries;
    }

    // Method to write a new query into database
    private void writeNewQuery(String question, int timeStamp, int numberOfAnswers) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mQuestionsReference = mDatabaseReference.child(QUESTIONS);

        // Calling getKey() on a push() reference returns the value of the auto-generated key.
        String key = mDatabaseReference.child(QUESTIONS).push().getKey();
        Query query = new Query(question, timeStamp, numberOfAnswers);

        mQuestionsReference.setValue(key);
        mQuestionsReference.child(key).child("question").setValue(query.getQuestion());
        mQuestionsReference.child(key).child("timeStamp").setValue(query.getTimeStamp());
        mQuestionsReference.child(key).child("numberOfAnswers").setValue(query.getNumberOfAnswers());
    }
}
