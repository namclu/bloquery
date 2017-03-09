package com.namclu.android.bloquery.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.ui.adapter.QueryItemAdapter;

import java.util.ArrayList;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * BloqueryActivity.java is the default main screen of the app.
 */
public class BloqueryActivity extends Activity {

    // A reference to an {@link ArrayAdapter}
    private QueryItemAdapter mQueryItemAdapter;

    // A reference to the {@link ListView} in the activity_bloquery.xml layout
    private ListView mQueryListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        // Initialize Views in the layout
        mQueryListView = (ListView) findViewById(R.id.list_query);

        // Create a new adapter that takes an empty list of earthquakes as input
        mQueryItemAdapter = new QueryItemAdapter(this, R.layout.query_list_item, new ArrayList());

        // Set the adapter on the {@link ListView} so the list can be populated in the user interface
        mQueryListView.setAdapter(mQueryItemAdapter);


    }
}
