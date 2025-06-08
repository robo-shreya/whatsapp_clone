/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.datamodel.model

/*
* The one that we want to convert from json
* */
data class MessageJson (
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timeStamp: String,
    val isMine: Boolean,
    val contentType: ContentType,
    val content: String,
    val contentDescription: String
) {
    enum class ContentType {
        TEXT, IMAGE
    }
}