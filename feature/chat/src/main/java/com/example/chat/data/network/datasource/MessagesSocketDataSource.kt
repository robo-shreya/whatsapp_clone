/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource

import com.example.chat.datamodel.WebSocketMessageModel
import com.example.chat.datamodel.model.MessageJsonResult
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/*
* represents a single websocket connection between server and client
* */
class MessagesSocketDataSource @Inject constructor(
    private val httpClient: HttpClient,
) {
    private lateinit var webSocketSession: DefaultClientWebSocketSession

    suspend fun connect(url: String): Flow<MessageJsonResult> {
        return httpClient.webSocketSession {
            url { takeFrom(url) }
        }.apply {
            webSocketSession = this
        }.incoming
            .receiveAsFlow()
            .mapNotNull { frame ->
                webSocketSession.handleMessage(frame)

            }.map { it.toDomain() }
    }

    suspend fun sendMessage(message: MessageJsonResult) {
        val webSocketMessage =
            WebSocketMessageModel.fromDomain(message)
        webSocketSession.converter
            ?.serialize(
                charset = Charsets.UTF_8,
                typeInfo = typeInfo<MessageJsonResult>(),
                value = webSocketMessage
            )?.let {
                webSocketSession.send(it)
            }
    }

    private suspend fun disconnect() {
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
