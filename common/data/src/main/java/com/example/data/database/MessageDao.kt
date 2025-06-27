/**
 * Created by shreyasrivastava on 22.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
* Dao is an interface that serves as a communication layer
* between application code and the database layer
* */

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(messageEntity: MessageEntity) : Long

    @Query("SELECT * FROM messages WHERE conversation_id = :conversationId ORDER BY timestamp ASC")
    fun getMessagesInConversation(conversationId: String) : List<MessageEntity>

    @Delete
    suspend fun deleteMessage(messageEntity: MessageEntity)

}