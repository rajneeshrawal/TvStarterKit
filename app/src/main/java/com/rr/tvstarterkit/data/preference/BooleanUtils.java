package com.rr.tvstarterkit.data.preference;

import android.text.TextUtils;
import android.util.Log;


public final class BooleanUtils {

    private static final String TAG = "BooleanUtils";

    /**
     * @param input
     * @return true if input is one of (T, True, TRUE, Y, yes, YES, 1, OK, ok, on, ON)
     */
    public static boolean isTrue(String input) {
        boolean result = false;
        try {
            if (!TextUtils.isEmpty(input)) {
                input = input.toLowerCase();
                if (input.equals("t")
                        || input.equals("true")
                        || input.equals("y")
                        || input.equals("yes")
                        || input.equals("ok")
                        || input.equals("on")
                        || input.equals("1")) {
                    result = true;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        return result;
    }

    public static boolean isFalse(String input) {
        return !isTrue(input);
    }
}
