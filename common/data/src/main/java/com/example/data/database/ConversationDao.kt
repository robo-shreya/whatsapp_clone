/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationDao {

    @Query("SELECT * FROM conversations ORDER BY last_message_time DESC")
    fun getAllConversations(): Flow<List<ConversationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConversation(conversationEntity: ConversationEntity) : Long

    @Delete
    suspend fun deleteConversation(conversationEntity: ConversationEntity)
}