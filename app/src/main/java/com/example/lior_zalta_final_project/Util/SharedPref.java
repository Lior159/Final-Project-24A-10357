package com.example.lior_zalta_final_project.Util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.example.lior_zalta_final_project.Model.User;

public class SharedPref {
//    private static volatile SharedPref instance = null;
//    private static final String USER_FILE = "USER_FILE";
//    private static final String KEY_USER = "KEY_USER";
//    private User user;
//    private SharedPreferences sharedPreferences;
//
//    private SharedPref(Context context) {
//        this.sharedPreferences = context.getSharedPreferences(USER_FILE, Context.MODE_PRIVATE);
//    }
//
//    public static void init(Context context) {
//        synchronized (SharedPref.class) {
//            if (instance == null) {
//                instance = new SharedPref(context);
//            }
//        }
//    }
//
//    public static SharedPref getInstance() {
//        return instance;
//    }
//
//    private void fetchFromFile() {
//        String res = getInstance().sharedPreferences.getString(KEY_USER, "");
//        if (res.equals(""))
//            this.user = new User();
//        else {
//            this.user = new Gson().fromJson(res, User.class);
//        }
//    }
}
