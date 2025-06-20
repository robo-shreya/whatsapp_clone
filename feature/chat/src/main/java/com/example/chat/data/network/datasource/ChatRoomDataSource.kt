/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource

import com.example.chat.data.network.domain.models.di.ChatModule.Companion.API_CHAT_ROOM_URL_NAME
import com.example.chat.data.network.domain.models.di.ChatModule.Companion.API_CLIENT
import com.example.chat.data.network.domain.models.ChatRoomModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Named

class ChatRoomDataSource @Inject constructor(
    @Named(API_CLIENT) private val client: HttpClient,
    @Named(API_CHAT_ROOM_URL_NAME) private val url: String
) {
    suspend fun getInitialChatRoom(id: String): ChatRoomModel {
        return client.get(url.format(id)).body()
    }
}