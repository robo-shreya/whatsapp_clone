/**
 * Created by shreyasrivastava on 28.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.backup

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.chat.data.network.domain.usecases.UploadMessagesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

//Assisted in Inject helps where you need to inject some
//dependencies but also need to provide some arguments at runtime

@HiltWorker
class UploadMessagesWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val uploadMessagesUseCase: UploadMessagesUseCase,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            uploadMessagesUseCase()
            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < MAX_RETRIES){
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }

    companion object {
        private const val MAX_RETRIES = 3
    }
}