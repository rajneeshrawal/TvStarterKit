package com.rr.tvstarterkit.data.preference

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import com.rr.tvstarterkit.application.ContextProvider.Companion.contextProvider
import com.rr.tvstarterkit.data.preference.BooleanUtils.isTrue

private val TAG = SharedPrefUtils::class.java.simpleName

class SharedPrefUtils {
    companion object {
        private var sharedPreferences: SharedPreferences? = null
        private var context: Context? = null
        private val preferences: SharedPreferences?
            get() {
                if (sharedPreferences == null) {
                    if (context == null) context = contextProvider?.provideContext()
                    sharedPreferences = context!!.getSharedPreferences(
                        SharedPrefKeys.Shared_Pref_name,
                        Context.MODE_PRIVATE
                    )
                }
                return sharedPreferences
            }
        private val editor: SharedPreferences.Editor?
            get() {
                val sp = preferences
                return sp?.edit()
            }

        fun getPref(key: String?, defValue: String?): String? {
            val sp = preferences
            return if (sp == null || TextUtils.isEmpty(key)) defValue else sp.getString(
                key,
                defValue
            )
        }

        fun getPref(key: String?, defValue: Int): Int {
            val sp = preferences
            return sp?.getInt(key, defValue) ?: defValue
        }

        fun getPref(key: String?, defValue: Long): Long {
            val sp = preferences
            return if (sp == null || TextUtils.isEmpty(key)) defValue else sp.getLong(key, defValue)
        }

        fun getPref(key: String, defValue: Boolean): Boolean {
            val sp = preferences
            return if (sp == null || TextUtils.isEmpty(key)) {
                defValue
            } else try {
                sp.getBoolean(key, defValue)
            } catch (e: ClassCastException) {
                Log.e(TAG, e.message, e)
                removePref(key)
                defValue
            }
        }

        fun getPrefStringSet(key: String?): Set<String>? {
            val sp = preferences ?: return null
            return sp.getStringSet(key, HashSet())
        }

        fun hasPref(key: String?): Boolean {
            val sp = preferences ?: return false
            return sp.contains(key)
        }

        fun putPref(key: String, `val`: String) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.putString(key, `val`)
            spe.apply()
            Log.d(TAG, "added string in SharedPref [ $key : $`val` ]")
        }

        fun putPref(key: String, `val`: Set<String?>) {
            val spe = editor ?: return
            spe.putStringSet(key, `val`)
            spe.apply()
            Log.d(TAG, "added Set<String> in SharedPref [ $key : $`val` ]")
        }

        fun putPref(key: String, `val`: Boolean) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.putBoolean(key, `val`)
            spe.apply()
            Log.d(TAG, "added boolean in SharedPref [ $key : $`val` ]")
        }

        fun putPref(key: String, `val`: Int) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.putInt(key, `val`)
            spe.apply()
            Log.d(TAG, "added int in SharedPref [ $key : $`val` ]")
        }

        fun putPref(key: String, `val`: Long) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.putLong(key, `val`)
            spe.apply()
            Log.d(TAG, "added long in SharedPref [ $key : $`val` ]")
        }

        fun removePref(key: String) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.remove(key)
            spe.apply()
            Log.d(TAG, "removed from SharedPref [ $key ]")
        }

        fun clear() {
            val spe = editor ?: return
            spe.clear()
            spe.apply()
            Log.d(TAG, "SharedPref cleared!")
        }

        fun isTrue(key: String?, defValue: String?): Boolean {
            return isTrue(getPref(key, defValue)!!)
        }

        fun isFalse(key: String?, defValue: String?): Boolean {
            return !isTrue(key, defValue)
        }

        fun equals(key: String?, defValue: String?, target: String?): Boolean {
            return TextUtils.equals(getPref(key, defValue), target)
        }

        /**
         * Retrieves a set of Strings from [SharedPreferences] and returns as a List.
         *
         * @param key the key in shared preferences to access the string set.
         * @return a list of <T> objects that were stored in shared preferences or an empty list if no
         * objects exists.
        </T> */
        fun getList(key: String?): Set<String>? {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return HashSet()
            val stringSet = getPrefStringSet(key)
            return if (stringSet!!.isEmpty()) {
                // Favoring mutability of the list over Collections.emptyList().
                HashSet()
            } else stringSet
        }

        /**
         * Saves a list of Strings into [SharedPreferences].
         *
         * @param key the key in shared preferences which the string set will be stored.
         */
        fun setList(set: Set<String?>?, key: String?) {
            val spe = editor
            if (spe == null || TextUtils.isEmpty(key)) return
            spe.putStringSet(key, set)
            spe.apply()
        }
    }
}