package com.namclu.android.bloquery.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Question;
import com.namclu.android.bloquery.ui.adapter.QuestionAdapter;
import com.namclu.android.bloquery.ui.fragment.AddInputDialogFragment;

import java.util.List;

/**
 * Created by namlu on 30-Jul-16.
 * <p>
 * BloqueryActivity.java is the default main screen of the app.
 */
public class BloqueryActivity extends AppCompatActivity
        implements
        ChildEventListener,
        QuestionAdapter.QuestionAdapterDelegate,
        AddInputDialogFragment.AddInputDialogListener {

    /* Constants */
    public static final String TAG = "BloqueryActivity";
    public static final String EXTRA_QUESTION_ID_KEY = "question_id_key";
    public static final String EXTRA_QUESTION_STRING = "question_string";
    public static final String EXTRA_USER_EMAIL = "user_email";

    private QuestionAdapter mQuestionAdapter;
    private RecyclerView mQueryRecyclerView;
    private DatabaseReference mQuestionsReference;
    private FirebaseAuth mCurrentUser = FirebaseAuth.getInstance();
    // Use DrawerLayout
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloquery);

        // Add Actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bloquery);
        setSupportActionBar(toolbar);

        // Initialize the adapter
        mQuestionAdapter = new QuestionAdapter();
        // Set BloqueryActivity(this) as QuestionAdapter's delegate
        mQuestionAdapter.setQuestionAdapterDelegate(this);

        // Initialize Views in the layout
        mQueryRecyclerView = (RecyclerView) findViewById(R.id.recycler_bloquery);

        // Set the layout, animator, and adapter for RecyclerView
        mQueryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQueryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mQueryRecyclerView.setAdapter(mQuestionAdapter);

        // Initialise database;
        mQuestionsReference = FirebaseDatabase.getInstance().getReference("questions");

        // Setup event listener
        mQuestionsReference.addChildEventListener(this);

        // Initialise ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_bloquery);

        // Initialise mDrawerToggle and implement DrawLayout listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, 0, 0);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mQuestionsReference.removeEventListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bloquery, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_question_bloquery) {
            showEditDialog();
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
     * Firebase: Required methods of ChildEventListener
     */
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Question question = dataSnapshot.getValue(Question.class);
        mQuestionAdapter.addQuestion(question);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    /*
     * Method from implementing QuestionAdapter.QuestionAdapterDelegate
     */
    @Override
    public void onItemClicked(int position, List<Question> questions) {

        // The current Question item
        Question questionItem = questions.get(position);
        String questionId = questionItem.getQuestionId();

        Intent intent = new Intent(this, SingleQuestionActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID_KEY, questionId);
        intent.putExtra(EXTRA_QUESTION_STRING, questionItem.getQuestionString());

        startActivity(intent);
    }

    @Override
    public void onFinishAddInput(String inputText) {
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter a question...", Toast.LENGTH_SHORT).show();
        } else {
            String key = mQuestionsReference.push().getKey();
            String userId = mCurrentUser.getCurrentUser().getUid();

            Question question = new Question(key, inputText, (long) System.currentTimeMillis(), 0, userId);
            mQuestionsReference.child(key).setValue(question);

            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddInputDialogFragment addInputDialogFragment = AddInputDialogFragment.newInstance("Ask a question");
        addInputDialogFragment.show(fm, TAG);
    }
}
