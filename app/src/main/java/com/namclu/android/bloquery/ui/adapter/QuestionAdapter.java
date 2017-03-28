package com.namclu.android.bloquery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Question;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 05-Mar-17.
 *
 * {@link QuestionAdapter} is an {@link RecyclerView.Adapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Question} objects.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionAdapterViewHolder> {

    public static interface QuestionAdapterDelegate {
        public void onItemClicked(int position, List<Question> queries);
    }

    /* private fields */
    private List<Question> mQuestions;

    // References to delegate objects
    private WeakReference<QuestionAdapterDelegate> mDelegate;

    public QuestionAdapter() {
        mQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        mQuestions.add(question);
        notifyDataSetChanged();
    }

    @Override
    public QuestionAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.question_list_item, parent, false);
        return new QuestionAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(QuestionAdapterViewHolder holder, int position) {
        holder.update(position, mQuestions.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    // Method to clear out previous data in RecyclerView
    public void clear() {
        int size = mQuestions.size();
        mQuestions.clear();
        notifyItemRangeRemoved(0, size);
    }

    /*
     * Getters and setter methods
     */
    public QuestionAdapterDelegate getQuestionAdapterDelegate() {
        if (mDelegate == null) {
            return null;
        }
        return mDelegate.get();
    }

    public void setQuestionAdapterDelegate(QuestionAdapterDelegate delegate) {
        mDelegate = new WeakReference<QuestionAdapterDelegate>(delegate);
    }

    class QuestionAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Reference to Question items
        TextView questionString;
        TextView timeStamp;
        TextView numAnswers;
        ImageView userImage;

        int position;

        public QuestionAdapterViewHolder(View itemView) {

            super(itemView);
            questionString = (TextView) itemView.findViewById(R.id.text_question_string);
            timeStamp = (TextView) itemView.findViewById(R.id.text_question_time_stamp);
            numAnswers = (TextView) itemView.findViewById(R.id.text_question_number_of_answers);
            userImage = (ImageView) itemView.findViewById(R.id.image_question_user_image);

            itemView.setOnClickListener(this);
        }

        void update(int position, Question question) {

            this.position = position;

            this.questionString.setText(question.getQuestionString());
            timeStamp.setText("Submitted: " + question.getTimeStamp());
            numAnswers.setText("Number of answers: " + question.getNumberOfAnswers());
            //userImage.setImageResource(question.getUserImageResId());
        }

        // Only method of View.OnClickListener
        @Override
        public void onClick(View view) {
            if (getQuestionAdapterDelegate() != null) {
                getQuestionAdapterDelegate().onItemClicked(position, mQuestions);
            }
        }

    }
}
