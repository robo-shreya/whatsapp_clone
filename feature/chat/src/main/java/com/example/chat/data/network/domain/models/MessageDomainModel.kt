/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.models

/*
* The one that we want to convert from json
* */
data class MessageDomainModel (
    val id: String? = null,
    val conversationId: String,
    val senderName: String,
    val senderAvatar: String,
    val timeStamp: String? = null,
    val isMine: Boolean,
    val contentType: ContentType,
    val content: String,
    val contentDescription: String
) {
    enum class ContentType {
        TEXT, IMAGE
    }
}