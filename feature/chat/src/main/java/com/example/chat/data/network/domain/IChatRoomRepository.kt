/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain

import com.example.chat.data.network.domain.models.ChatRoom

interface IChatRoomRepository {
    suspend fun getInitialChatRoom(id: String) : ChatRoom
}