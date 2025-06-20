/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ChatRoomModel (
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val lastMessages: List<WebSocketMessageModel>
) {
    fun toDomain() = ChatRoom (
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        lastMessages = lastMessages.map { it.toDomain() }
    )
}