package com.namclu.android.bloquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.ui.adapter.QueryAdapter;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * BloqueryActivity.java is the default main screen of the app.
 */
public class BloqueryActivity extends Activity {

    // A reference to an {@link ArrayAdapter}
    private QueryAdapter mQueryAdapter;

    // A reference to the {@link RecyclerView} in the activity_bloquery.xml layout
    private RecyclerView mQueryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        // Initialize Views in the layout
        mQueryRecyclerView = (RecyclerView) findViewById(R.id.recycler_query);

        // Set the layout, animator, and adapter for recyclerView
        mQueryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQueryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mQueryRecyclerView.setAdapter(mQueryAdapter);
    }
}
