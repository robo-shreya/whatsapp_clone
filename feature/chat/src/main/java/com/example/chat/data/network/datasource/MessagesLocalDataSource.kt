/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource

import com.example.chat.data.network.domain.models.MessageDomainModel
import com.example.data.database.MessageDao
import com.example.data.database.MessageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessagesLocalDataSource @Inject constructor(
    private val messageDao: MessageDao
) {

    fun getMessagesInConversation(
        conversationId: Int
    ): Flow<List<MessageDomainModel>> =
        messageDao.getMessagesInConversation(
            conversationId.toString()
        ).map { messageList -> messageList
            .map { message ->
                mapToDomain(message)
            }
        }

    suspend fun insertMessage(messageEntity: MessageEntity) : Long {
        return messageDao.insertMessage(messageEntity)
    }

    suspend fun deleteMessage(messageEntity: MessageEntity){
        messageDao.deleteMessage(messageEntity)
    }

    private fun mapToDomain(message: MessageEntity): MessageDomainModel {
        return MessageDomainModel(
            id = message.id.toString(),
            senderAvatar = message.senderAvatar,
            senderName = message.senderName,
            timeStamp = message.timestamp.toString(),
            isMine = message.isMine,
            content = message.content,
            contentType = MessageDomainModel.ContentType.TEXT,
            contentDescription = "",
            conversationId = message.conversationId,
        )
    }

    private fun mapFromDomain(message: MessageDomainModel): MessageEntity {
        return MessageEntity(
            id = message.id ?: "",
            content = message.content,
            // This is a simplification, in reality
            // there should be a conversion between the date format String to set it as Long
            timestamp = message.timeStamp?.toLong() ?: 0,
            senderName = message.senderName,
            senderAvatar = message.senderAvatar,
            isMine = message.isMine,
            conversationId = message.conversationId,
        )
    }
}