/**
 * Created by shreyasrivastava on 03.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.conversations.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.framework.Avatar


@Composable
fun ConversationItem(
    conversation: Conversation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Avatar(
            Modifier.padding(7.dp),
            imageUrl = conversation.avatarUrl,
            size = 50.dp,
            contentDescription = "${conversation.name}'s avatar"
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = conversation.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            )
            Text(text = conversation.message)
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column(horizontalAlignment = Alignment.End) {
            Text(text = conversation.timestamp)
            if (conversation.unreadCount > 0) {
                Box(

                    modifier = Modifier.padding(4.dp)
                        .background(color = MaterialTheme.colors.primary, shape = CircleShape)
                        .size(22.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = conversation.unreadCount.toString(),
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


data class Conversation(
    val id: String,
    val name: String,
    val message: String,
    val timestamp: String,
    val unreadCount: Int,
    val avatarUrl: String,
)