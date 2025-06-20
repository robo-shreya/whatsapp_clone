/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain

import com.example.chat.data.network.domain.models.MessageDomainModel
import kotlinx.coroutines.flow.Flow

interface IMessageRepository {

    suspend fun getMessages(chatId: String, userId: String): Flow<MessageDomainModel>

    suspend fun sendMessage(chatId: String, message: MessageDomainModel)

    suspend fun disconnect()
}