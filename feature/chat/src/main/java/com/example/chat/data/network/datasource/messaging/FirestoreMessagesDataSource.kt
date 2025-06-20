/**
 * Created by shreyasrivastava on 13.06.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.chat.data.network.datasource.messaging

import com.example.chat.data.network.domain.models.MessageDomainModel
import com.example.chat.data.network.model.FirestoreMessageModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreMessagesDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    fun getMessages(chatId: String, userId: String):
            Flow<MessageDomainModel> = callbackFlow {

        val chatRef = firestore.collection("chats")
            .document(chatId)
            .collection("messages")

        val query = chatRef.orderBy(
            "timestamp",
            Query.Direction.ASCENDING
        )

        val listenerRegistration = query
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull { doc ->
                    val message = doc.toObject(FirestoreMessageModel::class.java)
                    message?.copy(id = doc.id)
                } ?: emptyList()

                val domainMessages = messages.map {
                    it.toDomain(userId)
                }

                domainMessages.forEach {
                    try {
                        trySend(it).isSuccess
                    } catch (e: Exception) {
                        close(e)
                    }
                }
            }

        awaitClose { listenerRegistration.remove()}
    }

    fun sendMessage(chatId: String, message: MessageDomainModel) {
        val chatRef =
            firestore.collection("chats").document(chatId)
                .collection("messages")

        chatRef.add(FirestoreMessageModel.fromDomain(message))
    }
}