/**
 * Created by shreyasrivastava on 09.06.2025.
 *
 * Description: stores UI-related info in a lifecycle-conscious way.
 */
package com.example.chat.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat.data.network.domain.models.ChatRoom
import com.example.chat.data.network.domain.usecases.DisconnectMessages
import com.example.chat.data.network.domain.usecases.GetInitialChatRoomInfo
import com.example.chat.data.network.domain.usecases.RetrieveMessages
import com.example.chat.data.network.domain.usecases.SendMessage
import com.example.chat.data.network.domain.models.MessageDomainModel
import com.example.chat.ui.model.Chat
import com.example.chat.ui.model.MessageContent
import com.example.chat.ui.model.MessageUI
import com.example.chat.ui.model.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val retrieveMessages: RetrieveMessages,
    private val sendMessage: SendMessage,
    private val disconnectMessages: DisconnectMessages,
    private val getInitialChatRoomInfo: GetInitialChatRoomInfo,
) : ViewModel() {

    /* this will be my state holder */
    private val _messages = MutableStateFlow<List<MessageUI>>(emptyList())
    val messages: StateFlow<List<MessageUI>> = _messages

    private val _uiState = MutableStateFlow(Chat())
    val uiState: StateFlow<Chat> = _uiState

    private var messageCollectionJob: Job? = null

    private lateinit var chatRoom: ChatRoom

    suspend fun loadAndUpdateMessages() {
        messageCollectionJob?.cancel()
        messageCollectionJob =
            retrieveMessages(
                chatId = "TODO()",
                userId = "TODO()"
            )
                .map { it.toUI() }
                .flowOn(Dispatchers.IO)
                .onEach { _messages.update { it + it } }
                .launchIn(viewModelScope)
    }

    private fun MessageDomainModel.toUI() = MessageUI(
        id = id ?: "",
        senderName = senderName,
        senderAvatar = senderAvatar,
        timestamp = timeStamp ?: "",
        isMine = isMine,
        messageContent = getMessageContent()
    )

    private fun MessageDomainModel.getMessageContent(): MessageContent {
        return when (contentType) {
            MessageDomainModel.ContentType.TEXT ->
                MessageContent.TextMessage(content)

            MessageDomainModel.ContentType.IMAGE ->
                MessageContent.ImageMessage(content)
        }
    }

    fun onSendMessage(messageText: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = MessageDomainModel(
                conversationId = chatRoom.id,
                senderAvatar = TODO()/*user.avatar*/,
                senderName = TODO()/*user.name*/,
                isMine = true,
                contentType = MessageDomainModel.ContentType.TEXT,
                content = messageText,
                contentDescription = messageText
            )

            sendMessage(chatId = chatRoom.id, message = message)
        }
    }

    override fun onCleared() {
        messageCollectionJob?.cancel()
        viewModelScope.launch(Dispatchers.IO) {
            disconnectMessages()
        }
        super.onCleared()
    }

    fun loadChatInformation(id: String) {
        messageCollectionJob?.cancel()
        messageCollectionJob = viewModelScope.launch(Dispatchers.IO) {
            val chatRoom = getInitialChatRoomInfo(id)
            withContext(Dispatchers.Main) {
                _uiState.value = chatRoom.toUI()
                _messages.value = chatRoom.lastMessages.map {
                    it.toUI()
                }
                updateMessages()
            }
        }
    }

    private fun updateMessages() {
        messageCollectionJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                retrieveMessages(
                    userId = "",
                    chatId = ""
                    /*userId = getUserData.getData().id, chatId = chatRoom.id*/
                )
                    .map { it.toUI() }
                    .collect { message ->
                        withContext(Dispatchers.Main) {
                            _messages.value += message
                        }
                    }
            } catch (ie: Throwable) {
                Log.d(
                    "TODO",
                    "You can show here a message to the user indicating that an error has happened"
                )
            }
        }
    }
}