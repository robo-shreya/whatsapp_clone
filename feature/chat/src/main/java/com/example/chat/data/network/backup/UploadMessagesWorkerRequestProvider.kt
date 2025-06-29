/**
 * Created by shreyasrivastava on 28.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.backup

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.Operation
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.engine.callContext
import kotlinx.coroutines.currentCoroutineContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UploadMessagesWorkerRequestProvider @Inject constructor(
    @ApplicationContext private val appContext: Context
){

    fun create(): Operation {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.METERED)
            .build()

        val uploadMessagesRequest =
            PeriodicWorkRequestBuilder<UploadMessagesWorker>(
                7,
                TimeUnit.DAYS
            ).setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS,
                    TimeUnit.MILLISECONDS)
                .build()

        return WorkManager.getInstance(appContext).enqueue(uploadMessagesRequest)
    }
}