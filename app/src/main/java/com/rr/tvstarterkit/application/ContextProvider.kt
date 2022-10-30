package com.rr.tvstarterkit.application;

import android.content.Context;

public class ContextProvider {

    private Context applicationContext;

    private static ContextProvider mContextProvider;

    private ContextProvider() {
    }

    public static ContextProvider getContextProvider() {
        if (mContextProvider == null) {
            synchronized (ContextProvider.class) {
                mContextProvider = new ContextProvider();
            }
        }

        return mContextProvider;
    }

    public Context provideContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context context) {
        applicationContext = context;
    }

    public void destroyContext() {
        applicationContext = null;
    }
}
