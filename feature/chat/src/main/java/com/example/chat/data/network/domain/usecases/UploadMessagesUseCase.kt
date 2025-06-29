/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.domain.usecases

import com.example.chat.data.network.repository.BackupRepository
import javax.inject.Inject

class UploadMessagesUseCase @Inject constructor(
    private val backupRepository: BackupRepository
) {
    suspend operator fun invoke() {
        backupRepository.backupAllConversations()
    }
}