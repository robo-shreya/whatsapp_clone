/**
 * Created by shreyasrivastava on 12.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.repo

import com.example.data.FCMTokenDataSource
import javax.inject.Inject

class FCMTokenRepository @Inject constructor(
    private val tokenDataSource: FCMTokenDataSource
) {
    suspend fun getToken(): String? {
        return tokenDataSource.getFcmToken()
    }
}