/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.datamodel


/*
* The static data of the chat
* */
data class Chat(
    val id: String,
    val name: String,
    val avatar: String
)

/*
* The dynamic data of the chat (per message)
* */
data class Message(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val messageContent: MessageContent,
)

sealed class MessageContent {
    data class TextMessage(val message: String): MessageContent()

    data class ImageMessage(val imageUrl: String): MessageContent()
}