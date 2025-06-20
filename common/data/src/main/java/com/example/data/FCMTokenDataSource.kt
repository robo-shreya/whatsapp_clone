/**
 * Created by shreyasrivastava on 10.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/*
* for identifying users for the purpose of sending notifications
* */

class FCMTokenDataSource @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging =
        FirebaseMessaging.getInstance()
) {
    suspend fun getFcmToken(): String? {
        return try {
            firebaseMessaging.token.await()
        } catch (e: Exception) {
            null
        }
    }
}