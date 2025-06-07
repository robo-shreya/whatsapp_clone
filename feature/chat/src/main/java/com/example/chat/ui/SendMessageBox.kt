/**
 * Created by shreyasrivastava on 04.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.unit.dp

@Composable
fun SendMessageBox() {
    Box(
        modifier = Modifier
            .defaultMinSize()
            .padding(top = 0.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        val text = remember { mutableStateOf(value = String.toString()) }

        OutlinedTextField(
            value = text.value,
            onValueChange = { newText: String -> text.value = newText },
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .align(Alignment.CenterStart)
                .height(56.dp)
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(56.dp),
            onClick = {
                //send message
                text.value = ""
            }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                tint = MaterialTheme.colors.primary,
                contentDescription = "Send Message"
            )
        }
    }
}