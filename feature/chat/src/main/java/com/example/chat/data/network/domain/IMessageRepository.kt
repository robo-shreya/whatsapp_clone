/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain

import com.example.chat.datamodel.model.MessageJson
import kotlinx.coroutines.flow.Flow

interface IMessageRepository {

    suspend fun getMessages(): Flow<MessageJson>

    suspend fun sendMessage(message: MessageJson)

    suspend fun disconnect()
}