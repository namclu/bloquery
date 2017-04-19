package com.namclu.android.bloquery.api.model;

/**
 * Created by namlu on 31-Jul-16.
 * <p>
 * User.java is the data model the reflects the user's data after they've logged in.
 */
public class User {
    private String mUserId;
    private boolean mProfileImage;
    private String mProfileImageUrl;
    private String mUserEmail;
    private String mUserBio;
    private String mUserLocation;

    public User(boolean profileImage, String profileImageUrl, String userEmail) {
        setProfileImage(profileImage);
        setProfileImageUrl(profileImageUrl);
        setUserEmail(userEmail);
    }

    public User(String userId, boolean profileImage, String profileImageUrl, String userEmail,
                String userBio, String userLocation) {
        mUserId = userId;
        mProfileImage = profileImage;
        mProfileImageUrl = profileImageUrl;
        mUserEmail = userEmail;
        mUserBio = userBio;
        mUserLocation = userLocation;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public boolean hasProfileImage() {
        return mProfileImage;
    }

    public void setProfileImage(boolean profileImage) {
        mProfileImage = profileImage;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        mProfileImageUrl = profileImageUrl;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
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
