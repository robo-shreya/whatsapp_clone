/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.datamodel

import com.example.chat.datamodel.WebSocketMessageModel.Companion.TYPE_IMAGE
import com.example.chat.datamodel.WebSocketMessageModel.Companion.TYPE_TEXT
import com.example.chat.datamodel.model.MessageJson
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


    fun fromDomain(messageJson: MessageJson) =
        WebSocketMessageModel(
            id = messageJson.id,
            message = messageJson.content,
            senderName = messageJson.senderName,
            senderAvatar = messageJson.senderAvatar,
            timestamp = messageJson.timeStamp,
            isMine = messageJson.isMine,
            messageType = messageJson.fromContentType(),
            messageDescription = messageJson.contentDescription
        )
    }
    fun toDomain() = MessageJson(
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        isMine = isMine,
        content = message,
        contentType = toContentType(),
        contentDescription = messageDescription,
        timeStamp = timestamp,
    )

    private fun toContentType(): MessageJson.ContentType {
        return when (messageType) {
            TYPE_IMAGE -> MessageJson.ContentType.IMAGE
            else -> MessageJson.ContentType.TEXT
        }
    }
}

fun MessageJson.fromContentType(): String {
    return when (contentType) {
        MessageJson.ContentType.IMAGE -> TYPE_IMAGE
        else -> TYPE_TEXT
    }
}