/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource

import android.util.Log
import com.example.chat.data.network.domain.models.WebSocketMessageModel
import com.example.chat.data.network.domain.models.di.ChatModule.Companion.WEBSOCKET_CLIENT
import com.example.chat.data.network.domain.models.di.ChatModule.Companion.WEBSOCKET_URL_NAME
import com.example.chat.data.network.domain.models.MessageDomainModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.takeFrom
import io.ktor.serialization.deserialize
import io.ktor.util.reflect.typeInfo
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retryWhen
import okio.IOException
import javax.inject.Inject
import javax.inject.Named

/*
* represents a single websocket connection between server and client
* */
class MessagesSocketDataSource @Inject constructor(
    @Named(WEBSOCKET_CLIENT) private val httpClient:
    HttpClient,
    @Named(WEBSOCKET_URL_NAME) private val websocketUrl:
    String
) {
    companion object {
        const val RETRY_DELAY = 10000L
        const val MAX_RETRIES = 5
    }

    private lateinit var webSocketSession: DefaultClientWebSocketSession

    suspend fun connect(url: String): Flow<MessageDomainModel> {
        return flow {
            try {
                httpClient.webSocketSession {
                    url { takeFrom(url) }
                }.apply {
                    webSocketSession = this
                }.incoming
                    .receiveAsFlow()
                    .collect { frame ->
                        try {
                            val message =
                                webSocketSession.handleMessage(frame)
                                    ?.toDomain()
                            if (message != null) emit(message)
                        } catch (e: Exception) {
                            Log.e("MessageSocketDataFrame",
                                "connect: Error handling websocket frame",
                                e)
                        }
                    }
            } catch (e: Exception) {
                Log.e("MessageSocketDataFrame",
                    "connect: Error connecting to WebSocket",
                    e)
            }
        }.retryWhen { cause, attempt ->
            if (cause is IOException && attempt < MAX_RETRIES){
                delay(RETRY_DELAY)
                true
            } else {
                false
            }
        }.catch { e ->
            Log.e("MessageSocketDataFrame",
                "connect: Error in websocket flow", e)
        }
    }

/*    suspend fun sendMessage(message: MessageDomainModel) {
        val webSocketMessage =
            WebSocketMessageModel.fromDomain(message)
        webSocketSession.converter
            ?.serialize(
                charset = Charsets.UTF_8,
                typeInfo = typeInfo<MessageDomainModel>(),
                value = webSocketMessage
            )?.let {
                webSocketSession.send(it)
            }
    }*/

    suspend fun disconnect() {
        webSocketSession.close(
            CloseReason(
                CloseReason.Codes.NORMAL, "Disconnect"
            )
        )
    }

    private suspend fun DefaultClientWebSocketSession.handleMessage(
        frame: Frame
    ): WebSocketMessageModel? {
        return when (frame) {
            is Frame.Close -> {
                disconnect()
                null
            }

            is Frame.Text -> converter?.deserialize(frame)
            else -> null
        }

    }
}
