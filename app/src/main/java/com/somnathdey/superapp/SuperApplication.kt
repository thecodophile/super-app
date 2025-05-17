package com.somnathdey.superapp

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    companion object {
        const val TAG = "SuperApplication"
    }
}