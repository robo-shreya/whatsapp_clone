/**
 * Created by shreyasrivastava on 12.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.repo

import com.example.domain.IInternalTokenRepository

class InternalTokenRepository: IInternalTokenRepository {

    override suspend fun storeToken(userId: String, token: String) {
        //store in the data source of your choosing
    }
}