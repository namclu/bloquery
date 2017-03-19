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

    /* Constants */
    public static final String TAG = "QueryDataSource";
    public static final String QUESTIONS = "questions";

    /* private fields */
    private List<Query> mQueries;
    private DatabaseReference mDatabaseReference;
    private DatabaseReference mQuestionsReference;

    public QueryDataSource() {
        mQueries = new ArrayList<>();
    }

    public List<Query> getQueries() {
        return mQueries;
    }

    // Method to write a new query into database
    private void writeNewQuery(String question, int timeStamp, int numberOfAnswers) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mQuestionsReference = mDatabaseReference.child(QUESTIONS);

        String key = mDatabaseReference.push().getKey();
        Query query = new Query(key, question, timeStamp, numberOfAnswers);
        mQuestionsReference.child(key).setValue(query);
    }
}
