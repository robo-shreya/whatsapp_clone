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

@Composable
fun ChatScreen(
    chatId: String?,
    onBack: () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text("Alice")
                }
            )
        },
        bottomBar = {
            SendMessageBox()
        }
    ){ paddingValues ->
        ListOfMessages(paddingValues = paddingValues)
    }
}
