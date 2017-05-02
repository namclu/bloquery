# bloquery
An Android app (similar to Quora) where users can post questions and answers to questions. User login, questions and answers
data are stored in Firebase. (in progress)

## Getting Started

Get started by cloning the project to your local machine:

```
$ git clone https://github.com/namclu/bloquery.git
```

## Prerequisites

Android Studio
- minSdkVersion 17
- targetSdkVersion 25

Firebase

## Installing

1. Go to the [Firebase console](https://console.firebase.google.com/) and create a Firebase project, if you don't already have one. If you already have an existing Google project associated with your mobile app, click Import Google Project. Otherwise, click Create New Project.
2. Click Add Firebase to your Android app and follow the setup steps.
3. At the end, you'll download a google-services.json file.
4. Replace `google-services.json` in the project's module folder `app/` with your own copy.
5. Build & run.

If you're using the latest version of Android Studio (version 2.2 or later), use the [Firebase Assistant](https://developer.android.com/studio/write/firebase.html) to connect app to Firebase.

For more information about how to add Firebase to your project follow this [link](https://firebase.google.com/docs/android/setup).

## Built With

- [Google Firebase](https://console.firebase.google.com) is a mobile and web application development platform.

## Updates and Issues

- 02/05/2017 - Add feature in User Profile to take photo for user profile. App currently crashes when attempting to capture photo.

## Author(s)

- Nam Lu - [GitHub](https://github.com/namclu)


## Screenshots
<img src="/screenshots/sc_create_new_account.png" width="280" height="480"> <img src="/screenshots/sc_existing_user_login.png" width="280" height="480"> <img src="/screenshots/sc_questions_view.png" width="280" height="480"> <img src="/screenshots/sc_add_question_dialog.png" width="280" height="480"> <img src="/screenshots/sc_single_question_view.png" width="280" height="480">
