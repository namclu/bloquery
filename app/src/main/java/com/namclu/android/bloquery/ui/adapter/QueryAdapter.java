package com.namclu.android.bloquery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Query;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link QueryAdapter} is an {@link RecyclerView.Adapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Query} objects.
 */

public class QueryAdapter extends RecyclerView.Adapter<QueryAdapter.QueryAdapterViewHolder> {

    public static interface QueryAdapterDelegate {
        public void onItemClicked(QueryAdapter queryAdapter);
    }

    List<Query> mQueries;

    // References to delegate objects
    private WeakReference<QueryAdapterDelegate> mDelegate;

    public QueryAdapter() {
        mQueries = new ArrayList<>();
    }

    public void addQuery(Query query) {
        mQueries.add(query);
        notifyDataSetChanged();
    }

    @Override
    public QueryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_list_item, parent, false);
        return new QueryAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(QueryAdapterViewHolder queryAdapterViewHolder, int position) {
        queryAdapterViewHolder.update(mQueries.get(position));
    }

    @Override
    public int getItemCount() {
        return mQueries.size();
    }

    // Method to clear out previous data in RecyclerView
    public void clear() {
        int size = mQueries.size();
        mQueries.clear();
        notifyItemRangeRemoved(0, size);
    }

    /*
     * Getters and setter methods
     */
    public QueryAdapterDelegate getQueryAdapterDelegate() {
        if (mDelegate == null) {
            return null;
        }
        return mDelegate.get();
    }

    public void setQueryAdapterDelegate(QueryAdapterDelegate delegate) {
        mDelegate = new WeakReference<QueryAdapterDelegate>(delegate);
    }

    class QueryAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Reference to Query items
        TextView question;
        TextView timeStamp;
        TextView numAnswers;
        ImageView userImage;

        public QueryAdapterViewHolder(View itemView) {

            super(itemView);
            question = (TextView) itemView.findViewById(R.id.text_query_question);
            timeStamp = (TextView) itemView.findViewById(R.id.text_query_time_stamp);
            numAnswers = (TextView) itemView.findViewById(R.id.text_query_num_answers);
            userImage = (ImageView) itemView.findViewById(R.id.image_query_user_image);

            itemView.setOnClickListener(this);
        }

        void update(Query query) {

            question.setText(query.getQuestion());
            timeStamp.setText("Timestamp: " + query.getTimeStamp());
            numAnswers.setText("# of answers: " + query.getNumberOfAnswers());
            // userImage.setImageResource(query.getUserImageResId());
        }

        // Only method of View.OnClickListener
        @Override
        public void onClick(View view) {
            if (getQueryAdapterDelegate() != null) {
                getQueryAdapterDelegate().onItemClicked(QueryAdapter.this);
            }
        }

    }
}
