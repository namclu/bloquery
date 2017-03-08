package com.namclu.android.bloquery.ui.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Question;

import java.util.List;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link QuestionItemAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Question} objects.
 */

public class QuestionItemAdapter extends ArrayAdapter<Question> {


    public QuestionItemAdapter(@NonNull Context context,
                               @LayoutRes int resource,
                               @NonNull List<Question> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.query_list_item, parent, false);
        }

        // Get the {@link Earthquake} object located at this position in the list
        Question currentQuestionItem = getItem(position);

        /* Question */
        TextView questionTextView = (TextView) convertView.findViewById(R.id.text_query_question);
        questionTextView.setText(currentQuestionItem.getQuestion());

        /* Number of Answers */
        TextView numAnswersTextView = (TextView) convertView.findViewById(R.id.text_query_num_answers);
        numAnswersTextView.setText(currentQuestionItem.getNumberOfAnswers());

        /* Profile Pic of Questioner */
        ImageView userProfile = (ImageView) convertView.findViewById(R.id.image_query_user_profile);
        userProfile.setImageResource(currentQuestionItem.getUserImageResId());

        return convertView;
    }
}
