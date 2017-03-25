package com.namclu.android.bloquery.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.namclu.android.bloquery.R;

import static com.namclu.android.bloquery.R.id.button_add_question;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText mAnswerField;
    private Button mAnswerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // Initialise Database

        // Initialise Views
        mAnswerField = (EditText) findViewById(R.id.edit_add_question);
        mAnswerButton = (Button) findViewById(button_add_question);
    }
}
