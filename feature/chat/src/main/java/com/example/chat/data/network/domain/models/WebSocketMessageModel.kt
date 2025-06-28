/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.models

import com.example.chat.data.network.domain.models.WebSocketMessageModel.Companion.TYPE_IMAGE
import com.example.chat.data.network.domain.models.WebSocketMessageModel.Companion.TYPE_TEXT
import kotlinx.serialization.Serializable

@Serializable
open class WebSocketMessageModel(
    private val id: String,
    private val message: String,
    private val senderName: String,
    private val senderAvatar: String,
    private val timestamp: String,
    private val isMine: Boolean,
    private val messageType: String,
    private val messageDescription: String
) {
    companion object {
        const val TYPE_TEXT = "TEXT"
        const val TYPE_IMAGE = "IMAGE"


/*    fun fromDomain(messageDomainModel: MessageDomainModel) =
        WebSocketMessageModel(
            id = messageDomainModel.id,
            message = messageDomainModel.content,
            senderName = messageDomainModel.senderName,
            senderAvatar = messageDomainModel.senderAvatar,
            timestamp = messageDomainModel.timeStamp,
            isMine = messageDomainModel.isMine,
            messageType = messageDomainModel.fromContentType(),
            messageDescription = messageDomainModel.contentDescription
        )*/
    }
    fun toDomain() = MessageDomainModel(
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        isMine = isMine,
        content = message,
        contentType = toContentType(),
        contentDescription = messageDescription,
        timeStamp = timestamp,
        conversationId = id,
    )

    private fun toContentType(): MessageDomainModel.ContentType {
        return when (messageType) {
            TYPE_IMAGE -> MessageDomainModel.ContentType.IMAGE
            else -> MessageDomainModel.ContentType.TEXT
        }
    }
}

fun MessageDomainModel.fromContentType(): String {
    return when (contentType) {
        MessageDomainModel.ContentType.IMAGE -> TYPE_IMAGE
        else -> TYPE_TEXT
    }
}