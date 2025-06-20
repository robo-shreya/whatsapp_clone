/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.models.di

import com.example.chat.data.network.RestClient
import com.example.chat.data.network.WebSocketClient
import com.example.chat.data.network.domain.IChatRoomRepository
import com.example.chat.data.network.domain.IMessageRepository
import com.example.chat.data.network.repository.ChatRoomRepository
import com.example.chat.data.network.repository.MessagesRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ChatModule {
    companion object {
        const val WEBSOCKET_URL =
            "ws://whatspackt.com/chat/%s"
        const val WEBSOCKET_URL_NAME =
            "WEBSOCKET_URL"
        const val WEBSOCKET_CLIENT =
            "WEBSOCKET_CLIENT"
        const val API_CHAT_ROOM_URL_NAME =
            "http://whatspackt.com/chats/%s"
        const val API_CHAT_ROOM_NAME =
            "CHATROOM_URL"
        const val API_CLIENT = "API_CLIENT"
    }

    @Provides
    @Named(WEBSOCKET_CLIENT)
    fun providesWebsocketHttpClient(): HttpClient {
        return WebSocketClient.client
    }

    @Provides
    @Named(WEBSOCKET_URL_NAME)
    fun providesWebsocketURL(): String {
        return WEBSOCKET_URL
    }

    @Provides
    @Named(API_CLIENT)
    fun providesAPIHttpClient(): HttpClient {
        return RestClient.client
    }

    @Provides
    @Named(API_CHAT_ROOM_URL_NAME)
    fun providesChatRoomURL(): String {
        return API_CHAT_ROOM_URL_NAME
    }

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()

}

@InstallIn(SingletonComponent::class)
@Module
abstract class ChatBindingsModule {

    @Binds
    abstract fun providesChatRoomRepository(
        chatRoomRepository: ChatRoomRepository
    ): IChatRoomRepository

    @Binds
    abstract fun providesMessagesRepository(
        messagesRepository: MessagesRepository
    ): IMessageRepository
}