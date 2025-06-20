/**
 * Created by shreyasrivastava on 13.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAndStoreFCMToken @Inject constructor(
    private val fcmRepository: IFCMTokenRepository,
    private val internalTokenRepository: IInternalTokenRepository
) {
    suspend operator fun invoke(userId: String) = withContext(Dispatchers.IO) {
        val token = fcmRepository.getFCMToken()
        internalTokenRepository.storeToken(userId, token)
    }
}