package com.namclu.android.bloquery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Question;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link Question} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Question} objects.
 */

public class QuestionItemAdapter extends RecyclerView.Adapter<QuestionItemAdapter.QuestionItemAdapterViewHolder> {


    @Override
    public QuestionItemAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View inflate = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_item, viewGroup, false);
        return new QuestionItemAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(QuestionItemAdapterViewHolder holder, int position) {
        
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    // Inner class responsible for representing a single View created and returned by an Adapter
    class QuestionItemAdapterViewHolder extends RecyclerView.ViewHolder{

        // Create references to question_item.xml Views
        TextView question;
        TextView numberOfAnswers;
        ImageView userProfile;

        public QuestionItemAdapterViewHolder(View itemView) {
            super(itemView);

            question = (TextView) itemView.findViewById(R.id.text_question_item_question);
            numberOfAnswers = (TextView) itemView.findViewById(R.id.text_question_item_answers);
            userProfile = (ImageView) itemView.findViewById(R.id.image_question_item_user);
        }
    }
}
