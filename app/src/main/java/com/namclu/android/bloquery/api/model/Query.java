package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 05-Mar-17.
 *
 * Query class represents a single query submitted by a user in Bloquery.
 * A query consists of the query ID, question, timestamp, number of answers to the query,
 * and a user profile image of the user who submitted the query.
 */

public class Query {

    private static final int NO_ANSWERS = 0;
    private static final int NO_IMAGE = -1;

    /*
     * @param mQueryId          The assigned query ID
     * @param mQuestion         The question being asked in the query
     * @param mTimeStamp        The timestamp (in milliseconds) of when query was submitted
     * @param mNumberOfAnswers  The number of answers in response to query
     * @param mUserId           The user ID associated with user account who submitted the query
     * @param mUserImageResId   Resource ID for user image
     */
    private int mQueryId;
    private String mQuestion;
    private long mTimeStamp;
    private int mNumberOfAnswers = NO_ANSWERS;
    private long mUserId;
    private int mUserImageResId = NO_IMAGE;

    // Creates a new Query object
    public Query(int queryId, String question, long timeStamp, int numberOfAnswers, long userId, int userImageResId) {
        mQueryId = queryId;
        mQuestion = question;
        mTimeStamp = timeStamp;
        mNumberOfAnswers = numberOfAnswers;
        mUserId = userId;
        mUserImageResId = userImageResId;
    }

    public int getQueryId() {
        return mQueryId;
    }

    public void setQueryId(int queryId) {
        mQueryId = queryId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
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
