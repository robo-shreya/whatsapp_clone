/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.data.database.module

import android.content.Context
import com.example.data.database.ChatAppDatabase
import com.example.data.database.ConversationDao
import com.example.data.database.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext
        context: Context
    ): ChatAppDatabase {
        return ChatAppDatabase.getDatabase(context)
    }

    @Provides
    fun provideMessageDao(database: ChatAppDatabase) :
            MessageDao{
        return database.messageDao()
    }

    @Provides
    fun provideConversationDao(database: ChatAppDatabase) :
            ConversationDao{
        return database.conversationDao()
    }
}