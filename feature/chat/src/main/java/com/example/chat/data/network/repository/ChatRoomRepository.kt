/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.repository

import com.example.chat.data.network.datasource.ChatRoomDataSource
import com.example.chat.data.network.domain.IChatRoomRepository
import com.example.chat.data.network.domain.models.ChatRoom
import javax.inject.Inject

class ChatRoomRepository @Inject constructor(
    private val dataSource: ChatRoomDataSource
) : IChatRoomRepository {

    override suspend fun getInitialChatRoom(id: String): ChatRoom {
        val chatRoomApiModel = dataSource.getInitialChatRoom(id)

        return chatRoomApiModel.toDomain()
    }
}