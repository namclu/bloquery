package com.namclu.android.bloquery.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.QuestionDataSource;

import static com.namclu.android.bloquery.R.id.button_add_question;

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mAnswerField;
    private Button mAnswerButton;
    private QuestionDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        // Initialise Database

        // Initialise Views
        mAnswerField = (EditText) findViewById(R.id.edit_add_question);
        mAnswerButton = (Button) findViewById(button_add_question);

        mAnswerButton.setOnClickListener(this);
        dataSource = new QuestionDataSource();
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(mAnswerField.getText())) {
            Toast.makeText(this, "Please enter a question...", Toast.LENGTH_SHORT).show();
        } else {
            String questionString = mAnswerField.getText().toString();
            dataSource.writeNewQuestion(questionString, 0, 0);

            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
