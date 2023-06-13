package com.dp.gk.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
    private static final String PREF_NAME = "MyPreferences";
    private static final String THEME_PREFS_NAME = "ThemePrefs";
    private static final String THEME_STATUS_KEY = "themeStatus";
    // Method to save changed status of the app theme
    public static void saveTheme(Context context, boolean isDarkTheme) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THEME_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(THEME_STATUS_KEY, isDarkTheme);
        editor.apply();
    }

    // Method to retrieve the current status of the theme
    public static boolean getTheme(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(THEME_PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(THEME_STATUS_KEY, false);
    }

    // Method to clear all shared preferences
    public static void clearPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
