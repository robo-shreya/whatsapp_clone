/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui.model

import com.example.chat.data.network.domain.models.ChatRoom


/*
* The static data of the chat
* */
data class Chat(
    val id: String? = null,
    val name: String? = null,
    val avatar: String? = null,
)

/*
* The dynamic data of the chat (per message)
* */
data class MessageUI(
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

fun ChatRoom.toUI() = run {
    Chat(
        id = id,
        name = senderName,
        avatar = senderAvatar
    )
}