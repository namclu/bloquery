package com.namclu.android.bloquery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.namclu.android.bloquery.api.model.Question;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link Question} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Question} objects.
 */

public class QuestionItemAdapter extends RecyclerView.Adapter<QuestionItemAdapter.QuestionItemAdapterViewHolder> {


    @Override
    public QuestionItemAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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

        public QuestionItemAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
