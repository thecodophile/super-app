package com.somnathdey.superapp

import android.app.Application
import android.util.Log
import com.somnathdey.utilities.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SuperApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(message = "Application is launched")
    }
}