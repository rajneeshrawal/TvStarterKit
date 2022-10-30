package com.rr.tvstarterkit.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TvApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        ContextProvider.contextProvider?.setApplicationContext(this)
    }

}