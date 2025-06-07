/**
 * Created by shreyasrivastava on 14.02.2025.
 *
 * Description: responsible for holding Nav Host.
 */
package com.example.WhatsAppClone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chat.ui.ChatScreen
import com.example.conversations.ui.ConversationsListScreen
import com.example.framework.navigation.NavRoutes


@Composable
fun MyApplicationNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavRoutes.CHAT.replace("{chatId}", "1")) {

        addConversationsList(navHostController)

        addNewConversation(navHostController)

        addChat(navHostController)

    }
}

private fun NavGraphBuilder.addConversationsList(navHostController: NavHostController){

    composable(NavRoutes.CONVERSATIONS_LIST) { // case
        ConversationsListScreen(
            onNewConversationClick = {
                navHostController.navigate(
                    NavRoutes.CREATE_CHAT
                )
            },
            onConversationClick = { chatId ->
                navHostController.navigate(
                    NavRoutes.CHAT.replace("{chatId}", chatId)
                )
            }
        )
    }
}

private fun NavGraphBuilder.addNewConversation(
    navHostController: NavHostController
){
/*    composable(NavRoutes.CREATE_CHAT) {
        CreateChatScreen(onCreateConversation = {
            navHostController.navigate(NavRoutes.NewConversation)
        })
    }*/
}

private fun NavGraphBuilder.addChat(
    navHostController: NavHostController
){
    composable(
        route = NavRoutes.CHAT,
        arguments = listOf(navArgument(
            NavRoutes.ChatArgs.CHAT_ID
        ){
            type = NavType.StringType
        })
    ) { backStackEntry ->
        val chatId = backStackEntry.arguments?.getString(
            NavRoutes.ChatArgs.CHAT_ID)
        ChatScreen(chatId = chatId, onBack = {
            navHostController.popBackStack()
        })
    }
}
