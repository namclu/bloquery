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
    private DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    public QueryDataSource() {
        mQueries = new ArrayList<>();

        // Add elements to Query ArrayList
        /*mQueries.add(new Query("What is the square root of a pickle?", 7));
        mQueries.add(new Query("What is the square root of an orange?", 3));
        mQueries.add(new Query("What is the square root of a root?", 17));*/
    }

    public List<Query> getQueries() {
        return mQueries;
    }
}
