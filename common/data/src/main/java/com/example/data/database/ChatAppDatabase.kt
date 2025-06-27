/**
 * Created by shreyasrivastava on 22.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile

@Database(entities = [MessageEntity::class, ConversationEntity::class], version = 1)
abstract class ChatAppDatabase: RoomDatabase() {

    abstract fun messageDao(): MessageDao
    abstract fun conversationDao(): ConversationDao

    companion object {

        // @Volatile makes the value of this field atomic which means
        // it can be accessed by multiple threads and changes by one would be
        // visible in realtime to other threads accessing the value
        @Volatile
        private var INSTANCE: ChatAppDatabase? = null

        fun getDatabase(context: Context) : ChatAppDatabase{
            return INSTANCE ?: synchronized(this) {
                // only one thread can enter this block at one time
                // preventing creation of the instance of the database
                // from multiple threads
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChatAppDatabase::class.java,
                    "chat_database"
                ).build()

                // assignment helps to cache it so that teh next time its called,
                // it returns the previously made instance instead of creating a new one
                INSTANCE = instance
                instance
            }
        }

    }

}