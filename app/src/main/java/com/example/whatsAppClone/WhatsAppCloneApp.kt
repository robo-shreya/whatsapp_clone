package com.example.whatsAppClone

import android.app.Application
import android.util.Log
import com.example.chat.data.network.backup.UploadMessagesWorkerRequestProvider
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by shreyasrivastava on 14.02.2025.
 *
 * Description: Serves as a base for maintaining the global application state ex. Data or settings that needs to be maintained throughout the entire life cycle of the application.
 * Not created by default but essential for initializing tasks such as setting up DI frameworks or initializing libraries.
 */
@HiltAndroidApp
class WhatsAppCloneApp : Application() {

    @Inject
    lateinit var uploadMessagesWorkerRequestProvider: UploadMessagesWorkerRequestProvider

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        uploadMessagesWorkerRequestProvider.create()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            Log.e("💥UNCAUGHT", "Thread $thread threw uncaught", throwable)
            throwable.printStackTrace()
        }
    }
}