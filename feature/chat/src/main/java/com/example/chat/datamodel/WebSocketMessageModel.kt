/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.datamodel

import com.example.chat.datamodel.WebSocketMessageModel.Companion.TYPE_IMAGE
import com.example.chat.datamodel.WebSocketMessageModel.Companion.TYPE_TEXT
import com.example.chat.datamodel.model.MessageJsonResult
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


    fun fromDomain(messageJsonResult: MessageJsonResult) =
        WebSocketMessageModel(
            id = messageJsonResult.id,
            message = messageJsonResult.content,
            senderName = messageJsonResult.senderName,
            senderAvatar = messageJsonResult.senderAvatar,
            timestamp = messageJsonResult.timeStamp,
            isMine = messageJsonResult.isMine,
            messageType = messageJsonResult.fromContentType(),
            messageDescription = messageJsonResult.contentDescription
        )
    }
    fun toDomain() = MessageJsonResult(
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        isMine = isMine,
        content = message,
        contentType = toContentType(),
        contentDescription = messageDescription,
        timeStamp = timestamp,
    )

    private fun toContentType(): MessageJsonResult.ContentType {
        return when (messageType) {
            TYPE_IMAGE -> MessageJsonResult.ContentType.IMAGE
            else -> MessageJsonResult.ContentType.TEXT
        }
    }
}

fun MessageJsonResult.fromContentType(): String {
    return when (contentType) {
        MessageJsonResult.ContentType.IMAGE -> TYPE_IMAGE
        else -> TYPE_TEXT
    }
}