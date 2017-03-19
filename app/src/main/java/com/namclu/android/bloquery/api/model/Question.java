package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 05-Mar-17.
 *
 * Question class represents a single question object submitted by a user in Bloquery.
 * A Question object consists of the question ID, question, timestamp, number of answers to the
 * question, and a user profile image of the user who submitted the question.
 */

public class Question {

    private static final int NO_ANSWERS = 0;
    private static final int NO_IMAGE = -1;

    /*
     * @param mQuestionId       The assigned question ID
     * @param mQuestionString   The question string being asked in the question object
     * @param mTimeStamp        The timestamp (in milliseconds) of when question was submitted
     * @param mNumberOfAnswers  The number of answers in response to question
     * @param mUserId           The user ID associated with user account who submitted the question
     * @param mUserImageResId   Resource ID for user image
     */
    private String mQuestionId;
    private String mQuestionString;
    private long mTimeStamp;
    private int mNumberOfAnswers = NO_ANSWERS;
    private long mUserId;
    private int mUserImageResId = NO_IMAGE;

    // Creates a no-argument Question object
    public Question() {
        // Default constructor required for calls to DataSnapshot.getValue(Question.class)
    }

    // Creates a new Question object
    public Question(String questionString, int numberOfAnswers) {
        mQuestionString = questionString;
        mNumberOfAnswers = numberOfAnswers;
    }

    // Creates a new Question object
    public Question(String questionId, String questionString, long timeStamp, int numberOfAnswers) {
        mQuestionId = questionId;
        mQuestionString = questionString;
        mTimeStamp = timeStamp;
        mNumberOfAnswers = numberOfAnswers;
    }

    // Creates a new Question object
    public Question(String questionId, String questionString, long timeStamp, int numberOfAnswers, long userId, int userImageResId) {
        mQuestionId = questionId;
        mQuestionString = questionString;
        mTimeStamp = timeStamp;
        mNumberOfAnswers = numberOfAnswers;
        mUserId = userId;
        mUserImageResId = userImageResId;
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
