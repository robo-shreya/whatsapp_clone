/**
 * Created by shreyasrivastava on 12.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource.messaging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.framework.navigation.DeepLinks
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class WhatsappCloneMessagingService: FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "Chat_Message"
        const val CHANNEL_DESCRIPTION = "Receive a notification when message is received"
        const val CHANNEL_TITLE = "New chat message notification"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("TAG", "FCM registration token refreshed: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        // e.g.:
        // MyMessagingRepository.registerFcmToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        if (message.data.isNotEmpty()) {
            val senderName = message.data["senderName"]
            val messageContent = message.data["message"]
            val chatId = message.data["chatId"]
            val messageId = message.data["messageId"]

            if (chatId != null && messageId != null){
                showNotification(senderName, messageId, messageContent, chatId)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showNotification(
        senderName: String?,
        messageId: String,
        messageContent: String?,
        chatId: String
    ){
        val notificationManager = getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_TITLE,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        notificationManager.createNotificationChannel(channel)

        val deepLinkUrl =
            DeepLinks.chatRoute.replace("{chatId}", chatId)

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(deepLinkUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(
            this,
            CHANNEL_ID)
            .setContentTitle(senderName)
            .setContentText(messageContent)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(messageId.toInt(), notification)
    }
}