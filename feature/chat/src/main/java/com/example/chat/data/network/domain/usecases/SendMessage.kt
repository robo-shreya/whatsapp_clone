/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.usecases

import com.example.chat.data.network.domain.IMessageRepository
import com.example.chat.data.network.domain.models.MessageDomainModel
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val repository: IMessageRepository
) {
    suspend operator fun invoke(chatId: String, message: MessageDomainModel) {
        repository.sendMessage(chatId, message)
    }
}