package com.example.androiddemosecurecheckcashing.application

import android.app.Application
import android.content.Context

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext=this
    }

    companion object {
        private var appContext: Context? = null
        fun getDemoAppContext(): Context? {
            return appContext
        }
    }
}