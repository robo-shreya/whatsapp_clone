/**
 * Created by shreyasrivastava on 13.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.model

import com.example.chat.data.network.domain.models.MessageDomainModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import java.text.SimpleDateFormat
import java.util.Locale

data class FirestoreMessageModel(
    @Transient
    val id: String = "",

    @get:PropertyName("senderId")
    @set:PropertyName("senderId")
    var senderId: String = "",

    @get:PropertyName("senderName")
    @set:PropertyName("senderName")
    var senderName: String = "",

    @get:PropertyName("senderAvatar")
    @set:PropertyName("senderAvatar")
    var senderAvatar: String = "",

    @get:PropertyName("content")
    @set:PropertyName("content")
    var content: String = "",

    @get:PropertyName("timestamp")
    @set:PropertyName("timestamp")
    var timestamp: Timestamp = Timestamp.now(),


    ){

    companion object {
        fun fromDomain(message: MessageDomainModel) =
            FirestoreMessageModel(
                id = "",
                senderName = message.senderName,
                senderAvatar = message.senderAvatar,
                content = message.content
            )
    }

    fun toDomain(userId: String) = MessageDomainModel(
        id = id,
        senderName = senderName,
        senderAvatar = senderAvatar,
        timeStamp = timestamp.toDateString(),
        isMine = userId == senderId,
        contentType = MessageDomainModel.ContentType.TEXT,
        content = content,
        contentDescription = "",
        conversationId = id
    )

    private fun Timestamp.toDateString(): String {
        val formatter = SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss",
            Locale.getDefault())

        val date = toDate()

        return formatter.format(date)
    }
}

