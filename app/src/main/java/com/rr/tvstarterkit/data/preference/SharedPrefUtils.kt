package com.rr.tvstarterkit.data.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;


import com.rr.tvstarterkit.application.ContextProvider;

import java.util.HashSet;
import java.util.Set;


public class SharedPrefUtils {
    public static final String TAG = SharedPrefUtils.class.getSimpleName();

    private static SharedPreferences sharedPreferences;

    private static Context context;

    public SharedPrefUtils(Context context) {
        this.context = context;
    }


    public static SharedPreferences getPreferences() {
        if (sharedPreferences == null) {
            if (context == null)
                context = ContextProvider.getContextProvider().provideContext();
            sharedPreferences = context.getSharedPreferences(SharedPrefKeys.Shared_Pref_name, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    private static SharedPreferences.Editor getEditor() {
        SharedPreferences sp = getPreferences();
        return (sp != null) ? sp.edit() : null;
    }

    public static String getPref(String key, String defValue) {
        SharedPreferences sp = getPreferences();
        return (sp == null || TextUtils.isEmpty(key)) ? defValue : sp.getString(key, defValue);
    }

    public static int getPref(String key, int defValue) {
        SharedPreferences sp = getPreferences();
        return (sp == null) ? defValue : sp.getInt(key, defValue);
    }


    public static long getPref(String key, long defValue) {
        SharedPreferences sp = getPreferences();
        return (sp == null || TextUtils.isEmpty(key)) ? defValue : sp.getLong(key, defValue);
    }

    public static long getSettingPref(String key, long defValue) {
        SharedPreferences sp = getPreferences();
        return (sp == null || TextUtils.isEmpty(key)) ? defValue : sp.getLong(key, defValue);
    }


    public static boolean getPref(String key, boolean defValue) {
        SharedPreferences sp = getPreferences();
        if (sp == null || TextUtils.isEmpty(key)) {
            return defValue;
        }
        try {
            return sp.getBoolean(key, defValue);
        } catch (ClassCastException e) {
            Log.e(TAG, e.getMessage(), e);
            removePref(key);
            return defValue;
        }
    }

    public static Set<String> getPrefStringSet(String key) {
        SharedPreferences sp = getPreferences();
        if (sp == null)
            return null;

        return sp.getStringSet(key, new HashSet<String>());
    }


    public static boolean hasPref(String key) {
        SharedPreferences sp = getPreferences();
        if (sp == null)
            return false;
        return sp.contains(key);
    }

    public static void putPref(String key, String val) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.putString(key, val);
        spe.apply();
        Log.d(TAG, "added string in SharedPref [ " + key + " : " + val + " ]");
    }

    public static void putPref(String key, Set<String> val) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null)
            return;

        spe.putStringSet(key, val);
        spe.apply();
        Log.d(TAG, "added Set<String> in SharedPref [ " + key + " : " + val + " ]");
    }

    public static void putPref(String key, boolean val) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.putBoolean(key, val);
        spe.apply();
        Log.d(TAG, "added boolean in SharedPref [ " + key + " : " + val + " ]");
    }

    public static void putPref(String key, int val) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.putInt(key, val);
        spe.apply();
        Log.d(TAG, "added int in SharedPref [ " + key + " : " + val + " ]");
    }

    public static void putPref(String key, long val) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.putLong(key, val);
        spe.apply();
        Log.d(TAG, "added long in SharedPref [ " + key + " : " + val + " ]");
    }

    public static void removePref(String key) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.remove(key);
        spe.apply();
        Log.d(TAG, "removed from SharedPref [ " + key + " ]");
    }

    public static void clear() {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null)
            return;

        spe.clear();
        spe.apply();
        Log.d(TAG, "SharedPref cleared!");
    }

    public static boolean isTrue(String key, String defValue) {
        return BooleanUtils.isTrue(getPref(key, defValue));
    }

    public static boolean isFalse(String key, String defValue) {
        return !isTrue(key, defValue);
    }

    public static boolean equals(String key, String defValue, String target) {
        return TextUtils.equals(getPref(key, defValue), target);
    }


    /**
     * Retrieves a set of Strings from {@link SharedPreferences} and returns as a List.
     *
     * @param key the key in shared preferences to access the string set.
     * @return a list of <T> objects that were stored in shared preferences or an empty list if no
     * objects exists.
     */
    public static Set<String> getList(String key) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return new HashSet<>();

        Set<String> stringSet = getPrefStringSet(key);
        if (stringSet.isEmpty()) {
            // Favoring mutability of the list over Collections.emptyList().
            return new HashSet<>();
        }
        return stringSet;
    }

    /**
     * Saves a list of Strings into {@link SharedPreferences}.
     *
     * @param key the key in shared preferences which the string set will be stored.
     */
    public static void setList(Set<String> set, String key) {
        SharedPreferences.Editor spe = getEditor();
        if (spe == null || TextUtils.isEmpty(key))
            return;

        spe.putStringSet(key, set);
        spe.apply();
    }
}
