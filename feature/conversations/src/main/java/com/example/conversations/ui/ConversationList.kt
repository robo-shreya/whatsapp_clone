/**
 * Created by shreyasrivastava on 01.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.conversations.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun ConversationList(
    conversations: List<Conversation>,
) {
    LazyColumn {
        items(conversations) { conversation ->
            ConversationItem(
                conversation = conversation
            )
        }
    }
}
