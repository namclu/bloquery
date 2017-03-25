package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 20-Mar-17.
 *
 * Answer class represents a single Answer object submitted by a user in response to a
 * Question object in Bloquery.
 *
 */

public class Answer {

    private static final int NO_UP_VOTES = 0;
    private static final int NO_IMAGE = -1;

    /*
     * @param mAnswerId         The assigned question ID
     * @param mAnswerString     The answer string being in reply to the question object
     * @param mQuestionId       The question ID under which the answer was submitted under
     * @param mTimeStamp        The timestamp (in milliseconds) of when answer was submitted
     * @param mNumberUpVotes    The number of up votes in response to an answer
     * @param mUserId           The user ID associated with user account who submitted the question
     * @param mUserImageResId   Resource ID for user image
     * @param mUserUpVoted      Indicates if the user has previously up voted this answer
     */
    private String mAnswerId;
    private String mAnswerString;
    private String mQuestionId;
    private long mTimeStamp;
    private int mNumberUpVotes = NO_UP_VOTES;
    private long mUserId;
    private int mUserImageResId = NO_IMAGE;
    private boolean mUserUpVoted = false;

    // Creates a no-argument Answer object
    public Answer() {
        // Default constructor required for calls to DataSnapshot.getValue(Question.class)
    }

    // Create a new Answer object
    public Answer(String answerId, String answerString, int numberUpVotes, int timeStamp) {
        mAnswerId = answerId;
        mAnswerString = answerString;
        mNumberUpVotes = numberUpVotes;
        mTimeStamp = timeStamp;
    }

    // Create a new Answer object
    public Answer(String questionId, String answerId, String answerString, long timeStamp,
                  int numberUpVotes, long userId, int userImageResId, boolean userUpVoted) {
        mQuestionId = questionId;
        mAnswerId = answerId;
        mAnswerString = answerString;
        mTimeStamp = timeStamp;
        mNumberUpVotes = numberUpVotes;
        mUserId = userId;
        mUserImageResId = userImageResId;
        mUserUpVoted = userUpVoted;
    }

    public String getAnswerId() {
        return mAnswerId;
    }

    public void setAnswerId(String answerId) {
        mAnswerId = answerId;
    }

    public String getAnswerString() {
        return mAnswerString;
    }

    public void setAnswerString(String answerString) {
        mAnswerString = answerString;
    }

    public String getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(String questionId) {
        mQuestionId = questionId;
    }

    public long getTimeStamp() {
        return mTimeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        mTimeStamp = timeStamp;
    }

    public int getNumberUpVotes() {
        return mNumberUpVotes;
    }

    public void setNumberUpVotes(int numberUpVotes) {
        mNumberUpVotes = numberUpVotes;
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

    public boolean isUserUpVoted() {
        return mUserUpVoted;
    }

    public void setUserUpVoted(boolean userUpVoted) {
        mUserUpVoted = userUpVoted;
    }

    public static Answer provideTestAnswer() {
        return new Answer("14", "this is a test answer", 44, 334455);
    }
}
