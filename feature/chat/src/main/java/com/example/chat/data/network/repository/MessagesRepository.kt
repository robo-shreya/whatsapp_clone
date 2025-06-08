/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.repository

import com.example.chat.data.network.datasource.MessagesSocketDataSource
import com.example.chat.data.network.domain.IMessageRepository
import com.example.chat.datamodel.model.MessageJson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagesRepository @Inject constructor(
    private val dataSource: MessagesSocketDataSource
) : IMessageRepository{
    override suspend fun getMessages(): Flow<MessageJson> {
        return dataSource.connect("")
    }

    override suspend fun sendMessage(message: MessageJson){
        dataSource.sendMessage(message)
    }

    override suspend fun disconnect(){
        dataSource.disconnect()
    }
}