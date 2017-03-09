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
import com.namclu.android.bloquery.api.model.Query;

import java.util.List;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link QueryItemAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Query} objects.
 */

public class QueryItemAdapter extends ArrayAdapter<Query> {


    public QueryItemAdapter(@NonNull Context context,
                            @LayoutRes int resource,
                            @NonNull List<Query> objects) {
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

        // Get the {@link Query} object located at this position in the list
        Query currentQueryItem = getItem(position);

        /* Question */
        TextView questionTextView = (TextView) convertView.findViewById(R.id.text_query_question);
        questionTextView.setText(currentQueryItem.getQuestion());

        /* Number of Answers */
        TextView numAnswersTextView = (TextView) convertView.findViewById(R.id.text_query_num_answers);
        numAnswersTextView.setText(currentQueryItem.getNumberOfAnswers());

        /* User Profile Image */
        ImageView userProfile = (ImageView) convertView.findViewById(R.id.image_query_user_profile);
        userProfile.setImageResource(currentQueryItem.getUserImageResId());

        return convertView;
    }
}
