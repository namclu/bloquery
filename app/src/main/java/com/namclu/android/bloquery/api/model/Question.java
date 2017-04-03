package com.namclu.android.bloquery.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by namlu on 05-Mar-17.
 *
 * Question class represents a single Question object submitted by a user in Bloquery.
 *
 */

public class Question {

    private static final int NO_ANSWERS = 0;
    private static final int NO_IMAGE = -1;

    /*
     * @param mQuestionId       The assigned question ID
     * @param mQuestionString   The question string being asked in the question object
     * @param mTimeStamp        The timestamp (in milliseconds) of when question was submitted
     * @param mAnswers          A list of Answers associated with the current question
     * @param mNumberOfAnswers  The number of answers in response to question
     * @param mUserId           The user ID associated with user account who submitted the question
     * @param mUserImageResId   Resource ID for user image
     */
    private String mQuestionId;
    private String mQuestionString;
    private long mTimeStamp;
    private List<Answer> mAnswers = new ArrayList<>();
    private int mNumberOfAnswers = NO_ANSWERS;
    private String mUserId;
    private int mUserImageResId = NO_IMAGE;

    // Creates a no-argument Question object
    public Question() {
        // Default constructor required for calls to DataSnapshot.getValue(Question.class)
    }

    // Creates a new Question object
    public Question(String questionString, int numberOfAnswers) {
        setQuestionString(questionString);
        setNumberOfAnswers(numberOfAnswers);
    }

    // Creates a new Question object
    public Question(String key, String questionString, long timeStamp, int numberOfAnswers) {
        setQuestionId(key);;
        setQuestionString(questionString);
        setTimeStamp(timeStamp);
        setNumberOfAnswers(numberOfAnswers);
    }

    // Creates a new Question object
    public Question(String key, String questionString, long timeStamp, int numberOfAnswers,
                    String userId) {
        setQuestionId(key);;
        setQuestionString(questionString);
        setTimeStamp(timeStamp);
        setNumberOfAnswers(numberOfAnswers);
        setUserId(userId);
    }

    public String getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(String questionId) {
        mQuestionId = questionId;
    }

    public String getQuestionString() {
        return mQuestionString;
    }

    public void setQuestionString(String questionString) {
        mQuestionString = questionString;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public List<Answer> getAnswers() {
        return mAnswers;
    }

    public void setAnswers(List<Answer> answers) {
        mAnswers = answers;
    }

    public int getNumberOfAnswers() {
        return mNumberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        mNumberOfAnswers = numberOfAnswers;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public int getUserImageResId() {
        return mUserImageResId;
    }

    public void setUserImageResId(int userImageResId) {
        mUserImageResId = userImageResId;
    }
}
