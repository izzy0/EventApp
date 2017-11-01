package com.event.app.izhar.eventapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Created by khanp on 10/29/2017.
 */

public class UserSession {

    private static final String USER_SESSION = "usersession";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_FIRST_NAME = "keyfirstname";
    private static final String KEY_LAST_NAME = "keylastname";
    private static final String KEY_EMAIL = "keyemail";

    private static UserSession mInstance;
    private static Context mCtx;

    private UserSession(Context context){
        mCtx = context;
    }

    public static synchronized UserSession getmInstance(Context context){
        if(mInstance == null) {
            mInstance = new UserSession(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences userSession = mCtx.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userSession.edit();
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_LAST_NAME, user.getLastName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.apply();
    }

    public boolean isLoggedOn(){
        SharedPreferences userSession = mCtx.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        return userSession.getString(KEY_USERNAME, null) != null;
    }

    public  User getUser(){
        SharedPreferences userSession = mCtx.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
        return new User(
                userSession.getString(KEY_USERNAME, null),
                userSession.getString(KEY_PASSWORD, null),
                userSession.getString(KEY_FIRST_NAME, null),
                userSession.getString(KEY_LAST_NAME, null),
                userSession.getString(KEY_EMAIL, null)
        );
    }
//    public void logout(){
//        SharedPreferences userSession = mCtx.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = userSession.edit();
//        editor.clear();
//        editor.apply();
//        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
//    }

}
