/**
 * Created by shreyasrivastava on 12.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.domain

interface IFCMTokenRepository {
    suspend fun getFCMToken(): String
}