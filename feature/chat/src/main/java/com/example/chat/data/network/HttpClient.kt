/**
 * Created by shreyasrivastava on 05.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

object WebSocketClient {
    val client = HttpClient(OkHttp){
        install(WebSockets)
    }
}

object RestClient{
    val client = HttpClient{
        install(ContentNegotiation){
            json()
        }
    }
}