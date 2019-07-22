package com.example.hakathon22.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.hakathon22.Models.ProvidingJob;
import com.example.hakathon22.Models.SearchingJob;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "volleyregisterlogin";
    public static  String KEY_USERNAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    public static final String TOKEN = "keytoken";
    private static final String KEY_age = "keyAge";
    private static final String KEY_GENDER = "keyGender";
    private static final String KEY_ISCOMPANY = "keyRole";
    private static final String TYPE = "type";
    private static SharedPrefManager mInstance;
    private static Context ctx;

    private SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(ProvidingJob user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, user.getToken());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_USERNAME, user.getName());
        editor.apply();
    }
    public void userLogin(SearchingJob user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN, user.getToken());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_USERNAME, user.getName());
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOKEN, null) != null;
    }

    //this method will give the logged in user
    public SearchingJob getSearchingJob() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new SearchingJob(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_age, null) ,
                sharedPreferences.getString(KEY_EMAIL, null) ,
                sharedPreferences.getString(KEY_GENDER, null) ,
                sharedPreferences.getString(KEY_ISCOMPANY, "0"),
                sharedPreferences.getString(TYPE, null),
                sharedPreferences.getString(TOKEN, null));
    }
    public ProvidingJob getProvidingingJob() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new ProvidingJob(
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_age, null) ,
                sharedPreferences.getString(KEY_EMAIL, null) ,
                sharedPreferences.getString(KEY_GENDER, null) ,
                sharedPreferences.getString(KEY_ISCOMPANY, "0"),
                sharedPreferences.getString(TYPE, null),
                sharedPreferences.getString(TOKEN, null));
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        // ctx.startActivity(new Intent(ctx, MainActivity.class));
    }
}
