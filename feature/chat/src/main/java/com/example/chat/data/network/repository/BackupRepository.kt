/**
 * Created by shreyasrivastava on 27.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.repository

import com.example.chat.data.network.datasource.StorageDataSource
import com.example.data.database.ConversationDao
import com.example.data.database.MessageDao
import com.google.gson.Gson
import java.io.File
import javax.inject.Inject
import kotlin.io.path.writeText

class BackupRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val conversationDao: ConversationDao,
    private val storageDataSource: StorageDataSource
) {
    private val gson = Gson()

    suspend fun backupAllConversations() {

        // get all conversations
        val conversations = conversationDao.getAllConversations()

        //backup all conversations
        conversations.collect { conversationList ->
            for (conversation in conversationList){
                val messages = messageDao.getMessagesInConversation(
                    conversation.id
                )

                // creating a JSON representation of the messages
                val messagesJson = gson.toJson(messages)

                // create temp file and writing JSON to it
                val tempFile = myCreateTempFile("messages", ".json")

                tempFile.writeText(messagesJson)

                //upload the file to firebase
                val remotePath = "conversations/${conversation.id}/messages.json"
                storageDataSource.uploadFile(localFile = tempFile, remotePath = remotePath)

                // delete the local file
                tempFile.delete()
            }
        }
    }

    private fun myCreateTempFile(prefix: String, suffix: String): File {
        // directory where we want the file
        val tempDir = System.getProperty("java.io.tmpdir")?.let { File(it) }

        //create temp file with given prefix and suffix
        return File.createTempFile(prefix, suffix, tempDir)
    }
}