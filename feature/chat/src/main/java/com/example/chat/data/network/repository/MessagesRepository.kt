/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.repository

import com.example.chat.data.network.datasource.MessagesLocalDataSource
import com.example.chat.data.network.datasource.messaging.FirestoreMessagesDataSource
import com.example.chat.data.network.domain.IMessageRepository
import com.example.chat.data.network.domain.models.MessageDomainModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val dataSource: FirestoreMessagesDataSource,
    private val localDataSource: MessagesLocalDataSource
) : IMessageRepository{
    override suspend fun getMessages(
        chatId: String,
        userId: String
    ): Flow<MessageDomainModel> {

        return dataSource.getMessages(
            chatId,
            userId
        )
    }

    override suspend fun sendMessage(
        chatId: String,
        message: MessageDomainModel
    ){
        dataSource.sendMessage(
            chatId, message
        )
    }

    override suspend fun disconnect(){
        //firestore data source is automatically disconnected as soon
        //as the flow has no subscribers
    }
}