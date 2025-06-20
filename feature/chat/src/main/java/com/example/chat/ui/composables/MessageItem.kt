/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import com.example.framework.Avatar
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chat.ui.model.MessageContent
import com.example.chat.ui.model.MessageUI

@Composable
fun MessageItem(messageUI: MessageUI) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement =
        if (messageUI.isMine) Arrangement.End
        else Arrangement.Start
    ) {
        Avatar(
            imageUrl = messageUI.senderAvatar,
            size = 40.dp,
            contentDescription =
            "${messageUI.senderName}'s avatar",
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            if (messageUI.isMine) {
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                Text(
                    text = messageUI.senderName,
                    fontWeight = FontWeight.Bold
                )
            }
            when (val content = messageUI.messageContent) {
                is MessageContent.TextMessage -> {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (messageUI.isMine) {
                            MaterialTheme.colors.primarySurface
                        } else {
                            MaterialTheme.colors.secondary
                        }
                    ) {
                        Text(
                            text = content.message,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.6f),
                            color = if (messageUI.isMine)
                                MaterialTheme.colors.onPrimary else
                                Color.White
                        )
                    }
                }

                is MessageContent.ImageMessage -> {
                    AsyncImage(
                        model = content.imageUrl,
                        contentDescription = "image message",
                        modifier = Modifier
                            .size(178.dp)
                            .clip(RectangleShape),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Text(
                text = messageUI.timestamp,
                fontSize = 12.sp
            )
        }
    }
}