package com.namclu.android.bloquery.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Answer;
import com.namclu.android.bloquery.ui.adapter.AnswerAdapter;
import com.namclu.android.bloquery.ui.fragment.AddInputDialogFragment;

public class SingleQuestionActivity extends AppCompatActivity implements
        ChildEventListener,
        AddInputDialogFragment.AddInputDialogListener {

    /* Constants */
    public static final String TAG = "SingleQuestionActivity";

    /* private fields */
    private TextView userEmail;
    private TextView questionString;
    private TextView timeStamp;
    private TextView numAnswers;
    private ImageView userImage;

    private String mQuestionId;
    private int position;

    private AnswerAdapter mAnswerAdapter;

    // Firebase stuff
    private DatabaseReference mQuestionsReference;
    private DatabaseReference mAnswersReference;
    private FirebaseAuth mCurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_question);

        // Get the intent data from BloqueryActivity
        mQuestionId = getIntent().getStringExtra("question_id_key");

        // Initialise Views
        userEmail = (TextView) findViewById(R.id.text_single_question_user_email);
        questionString = (TextView) findViewById(R.id.text_single_question_string);
        timeStamp = (TextView) findViewById(R.id.text_single_question_time_stamp);
        numAnswers = (TextView) findViewById(R.id.text_single_question_num_answers);
        userImage = (ImageView) findViewById(R.id.image_single_question_user_image);

        // Initialise Firebase stuff
        mQuestionsReference = FirebaseDatabase.getInstance().getReference("questions").child(mQuestionId);
        mAnswersReference = FirebaseDatabase.getInstance().getReference("answers").child(mQuestionId);
        mCurrentUser = FirebaseAuth.getInstance();

        // Set listener on Database
        mAnswersReference.addChildEventListener(this);

        /* RecyclerView stuff */
        mAnswerAdapter = new AnswerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_answers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAnswerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Set values for this single Question object
        questionString.setText(getIntent().getStringExtra("question_string"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_question, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showEditDialog();

        return super.onOptionsItemSelected(item);
    }

    /*
     * Firebase: Required methods of ChildEventListener
     */
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Answer answer = dataSnapshot.getValue(Answer.class);
        mAnswerAdapter.addAnswer(answer);
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

    @Override
    public void onFinishAddInput(String inputText) {
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter an answer...", Toast.LENGTH_SHORT).show();
        } else {
            String userId = mCurrentUser.getCurrentUser().getUid();
            String key = mAnswersReference.push().getKey();

            Answer answer = new Answer(userId, key, inputText, (long) System.currentTimeMillis(), 0);
            mAnswersReference.child(key).setValue(answer);

            Toast.makeText(this, "Answer added!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddInputDialogFragment addInputDialogFragment = AddInputDialogFragment.newInstance("Add an answer");
        addInputDialogFragment.show(fm, TAG);
    }
}
