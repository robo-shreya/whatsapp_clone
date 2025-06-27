/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "conversations")

data class ConversationEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "last_message_time")
    val lastMessageTime: Long
)