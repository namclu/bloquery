package com.namclu.android.bloquery.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.AnswerDataSource;

public class SubmitAnswerActivity extends AppCompatActivity {

    String questionId;
    AnswerDataSource answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_answer);

        questionId = getIntent().getStringExtra("question_id");
    }
}
