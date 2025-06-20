/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui.composables

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
import com.example.chat.ui.model.MessageContent
import com.example.chat.ui.model.MessageUI

@Composable
fun ListOfMessages (
    messages: List<MessageUI>,
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
                items(messages) { message ->
                    MessageItem(message)
                }
            }
        }
    }
}

/*
fun getFakeMessages(): List<MessageUI> {
    return listOf(
        MessageUI(
            id = "1",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:11",
            messageContent = MessageContent.TextMessage(
                message = "Hi, how are you"
            )
        ),
        MessageUI(
            id = "2",
            senderName = "Lucy",
            senderAvatar = "https://i.pravatar.cc/300?img=2",
            isMine = true,
            timestamp = "11:12",
            messageContent = MessageContent.TextMessage(
                message = "Hola! I'm good :D"
            )
        ),
        MessageUI(
            id = "3",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:12",
            messageContent = MessageContent.TextMessage(
                message = "What's up with you, would you like to go to this pizza fest"
            )
        ),
        MessageUI(
            id = "4",
            senderName = "Alice",
            senderAvatar = "https://i.pravatar.cc/300?img=1",
            isMine = false,
            timestamp = "11:13",
            messageContent = MessageContent.ImageMessage(
                imageUrl = "https://www.theconnectioninc.org/wp-content/uploads/PizzaFest.jpg"
            )
        ),
        MessageUI(
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
}*/
