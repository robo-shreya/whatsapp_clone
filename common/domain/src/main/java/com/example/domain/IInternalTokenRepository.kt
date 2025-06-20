/**
 * Created by shreyasrivastava on 12.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.domain

interface IInternalTokenRepository {
    suspend fun storeToken(userId: String, token: String)
}