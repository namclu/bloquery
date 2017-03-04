package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 31-Jul-16.
 *
 * UserAccount.java is the data model the reflects the user's data after they've logged in.
 *
 */
public class UserAccount {
    private long mUserID;
    private boolean mUserImage;
    private String mUserBio;
    private String mUserLocation;

    public UserAccount(long userID, boolean userImage, String userBio, String userLocation) {
        mUserID = userID;
        mUserImage = userImage;
        mUserBio = userBio;
        mUserLocation = userLocation;
    }

    public long getUserID() {
        return mUserID;
    }

    public void setUserID(long userID) {
        mUserID = userID;
    }

    public boolean isUserImage() {
        return mUserImage;
    }

    public void setUserImage(boolean userImage) {
        mUserImage = userImage;
    }

    public String getUserBio() {
        return mUserBio;
    }

    public void setUserBio(String userBio) {
        mUserBio = userBio;
    }

    public String getUserLocation() {
        return mUserLocation;
    }

    public void setUserLocation(String userLocation) {
        mUserLocation = userLocation;
    }
}
