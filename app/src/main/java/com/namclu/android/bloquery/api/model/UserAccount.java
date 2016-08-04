package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 31-Jul-16.
 *
 * UserAccount.java is the data model the reflects the user's data after they've logged in.
 *
 */
public class UserAccount {
    private long userID;
    private boolean userImage;
    private String userBio;
    private String userLocation;

    public UserAccount(long userID, boolean userImage, String userBio, String userLocation) {
        this.userID = userID;
        this.userImage = userImage;
        this.userBio = userBio;
        this.userLocation = userLocation;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public boolean isUserImage() {
        return userImage;
    }

    public void setUserImage(boolean userImage) {
        this.userImage = userImage;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
