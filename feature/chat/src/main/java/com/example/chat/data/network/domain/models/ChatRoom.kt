/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.models

data class ChatRoom (
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val lastMessages: List<MessageDomainModel>
)