package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 05-Mar-17.
 *
 * Question class represents a single question submitted by a user in Bloquery
 */

public class Question {

    private static final int NO_ANSWERS = 0;
    private static final int NO_IMAGE = -1;

    /*
     * @param mQuestionId       The question ID assigned
     * @param mQuestion         The question in String format
     * @param mTimeInMills      The time (in milliseconds) of when questions was submitted
     * @param mNumberOfAnswers  The number of answers for the question
     * @param mUserId           The user ID associated with user account who submitted the question
     * @param mUserImageResId   Resource ID for user image
     */
    private int mQuestionId;
    private String mQuestion;
    private long mTimeInMills;
    private int mNumberOfAnswers = NO_ANSWERS;
    private long mUserId;
    private int mUserImageResId = NO_IMAGE;

    // Creates a new Question object
    public Question(int questionId, String question, long timeInMills, int numberOfAnswers, long userId, int userImageResId) {
        mQuestionId = questionId;
        mQuestion = question;
        mTimeInMills = timeInMills;
        mNumberOfAnswers = numberOfAnswers;
        mUserId = userId;
        mUserImageResId = userImageResId;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public long getTimeInMills() {
        return mTimeInMills;
    }

    public void setTimeInMills(long timeInMills) {
        mTimeInMills = timeInMills;
    }

    public int getNumberOfAnswers() {
        return mNumberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        mNumberOfAnswers = numberOfAnswers;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public int getUserImageResId() {
        return mUserImageResId;
    }

    public void setUserImageResId(int userImageResId) {
        mUserImageResId = userImageResId;
    }
}
