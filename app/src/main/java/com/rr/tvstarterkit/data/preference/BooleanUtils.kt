package com.rr.tvstarterkit.data.preference

import android.text.TextUtils
import android.util.Log
import java.lang.Exception
import java.util.*

object BooleanUtils {
    private const val TAG = "BooleanUtils"

    /**
     * @param input
     * @return true if input is one of (T, True, TRUE, Y, yes, YES, 1, OK, ok, on, ON)
     */
    @JvmStatic
    fun isTrue(input: String): Boolean {
        var input = input
        var result = false
        try {
            if (!TextUtils.isEmpty(input)) {
                input = input.lowercase(Locale.getDefault())
                if (input == "t" || input == "true" || input == "y" || input == "yes" || input == "ok" || input == "on" || input == "1") {
                    result = true
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, e.message!!)
        }
        return result
    }

    fun isFalse(input: String): Boolean {
        return !isTrue(input)
    }
}