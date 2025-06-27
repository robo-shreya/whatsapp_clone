package com.example.whatsAppClone

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by shreyasrivastava on 14.02.2025.
 *
 * Description: Serves as a base for maintaining the global application state ex. Data or settings that needs to be maintained throughout the entire life cycle of the application.
 * Not created by default but essential for initializing tasks such as setting up DI frameworks or initializing libraries.
 */
@HiltAndroidApp
class WhatsAppCloneApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("💥UNCAUGHT", "Thread $thread threw uncaught", throwable)
            throwable.printStackTrace()
        }
    }
}