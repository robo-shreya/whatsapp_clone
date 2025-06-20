/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.usecases

import com.example.chat.data.network.domain.IChatRoomRepository
import com.example.chat.data.network.domain.models.ChatRoom
import javax.inject.Inject

class GetInitialChatRoomInfo @Inject constructor(
    private val repository: IChatRoomRepository
) {
    suspend operator fun invoke(id: String): ChatRoom {
        return repository.getInitialChatRoom(id)
    }
}