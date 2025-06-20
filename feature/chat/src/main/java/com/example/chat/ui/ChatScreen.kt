/**
 * Created by shreyasrivastava on 07.03.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.chat.ui.composables.ListOfMessages
import com.example.chat.ui.composables.SendMessageBox
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
    chatId: String?,
    onBack: () -> Unit
) {

    val messages by viewModel.messages.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadChatInformation(chatId.orEmpty())
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(uiState.name.orEmpty())
                }
            )
        },
        bottomBar = {
            SendMessageBox { viewModel.onSendMessage(it) }
        }
    ){ paddingValues ->
        ListOfMessages(paddingValues = paddingValues,
            messages = messages)
    }
}
