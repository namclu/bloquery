package com.namclu.android.bloquery.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.namclu.android.bloquery.R;
import com.namclu.android.bloquery.api.model.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 22-Mar-17.
 */

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.AnswerAdapterViewHolder> {

    /* Private fields */
    private List<Answer> mAnswers;

    public AnswerAdapter() {
        mAnswers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        mAnswers.add(answer);
        notifyDataSetChanged();
    }

    // Add a List<Answer>
    public void addAnswers(List<Answer> answers) {
        mAnswers.addAll(answers);
        notifyDataSetChanged();
    }

    @Override
    public AnswerAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.answer_list_item, parent, false);
        return new AnswerAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(AnswerAdapterViewHolder holder, int position) {
        holder.update(position, mAnswers.get(position));
    }

    @Override
    public int getItemCount() {
        return mAnswers.size();
    }

    class AnswerAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView answerString;
        ImageView userImage;
        TextView numUpVotes;
        ImageButton buttonUpVote;

        int position;

        public AnswerAdapterViewHolder(View itemView) {
            super(itemView);

            answerString = (TextView) itemView.findViewById(R.id.text_answer_string);
            userImage = (ImageView) itemView.findViewById(R.id.image_answer_user_image);
            numUpVotes = (TextView) itemView.findViewById(R.id.text_number_of_up_votes);
            buttonUpVote = (ImageButton) itemView.findViewById(R.id.button_up_vote);
        }

        void update(int position, Answer answer) {
            this.position = position;

            answerString.setText(answer.getAnswerString());
            //userImage.setImageResource(answer.getUserImageResId());
            numUpVotes.setText(answer.getNumberUpVotes() + " votes");
            //buttonUpVote.setImageResource();
        }
    }
}
