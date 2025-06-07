/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import com.example.chat.datamodel.Message
import com.example.chat.datamodel.MessageContent

@Composable
fun ListOfMessages (
    paddingValues: PaddingValues
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {
                items(
                    getFakeMessages()
                ){ message ->
                    MessageItem(message = message)
                }
            }
        }
    }
}

fun getFakeMessages(): List<Message> {
    return listOf(
        Message(
            id = "1",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:11",
            messageContent = MessageContent.TextMessage(
                message = "Hi, how are you"
            )
        ),
        Message(
            id = "2",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=2",
            isMine = true,
            timestamp = "11:12",
            messageContent = MessageContent.TextMessage(
                message = "Hola! I'm good :D"
            )
        ),
        Message(
            id = "3",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:12",
            messageContent = MessageContent.TextMessage(
                message = "What's up with you, would you like to go to this pizza fest"
            )
        ),
        Message(
            id = "4",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:13",
            messageContent = MessageContent.ImageMessage(
                imageUrl = "https://www.theconnectioninc.org/wp-content/uploads/PizzaFest.jpg"
            )
        ),
        Message(
            id = "5",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=2",
            isMine = true,
            timestamp = "11:14",
            messageContent = MessageContent.TextMessage(
                message = "Yes, definitely. See you this weekend :)"
            )
        ),
    )
}