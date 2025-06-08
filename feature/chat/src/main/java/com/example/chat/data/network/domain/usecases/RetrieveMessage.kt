/**
 * Created by shreyasrivastava on 08.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.usecases

import com.example.chat.data.network.domain.IMessageRepository
import com.example.chat.datamodel.model.MessageJson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveMessage @Inject constructor(
    private val repository: IMessageRepository
){
    suspend operator fun invoke(): Flow<MessageJson>{
        return repository.getMessages()
    }
}