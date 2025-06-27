/**
 * Created by shreyasrivastava on 22.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


//@Entity declares that this class will be treated as a table in the database
@Entity(tableName = "messages",

    // foreign key represents the relationship with another table
    // parent key is the column that we reference through the foreign key
    // child columns specifies the child entity that holds the foreign key
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("conversation_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],

    //creating an index on conversation_id to reference it faster
    indices = [
        Index(value = ["conversation_id"])
    ])
data class MessageEntity (

    @PrimaryKey(autoGenerate = true)
    val id: String,

    @ColumnInfo(name = "conversation_id")
    val conversationId: String,

    @ColumnInfo(name = "sender")
    val sender: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long,
)