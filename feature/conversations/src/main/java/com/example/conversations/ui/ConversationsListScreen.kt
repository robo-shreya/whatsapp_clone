/**
 * Created by shreyasrivastava on 07.03.2025.
 *
 * Description: [Brief description of this class].
 */
package com.example.conversations.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.framework.R

/**
 * Scaffold - helps create a consistent layout by providing slots for common UI elements.
 * helps create a consistent look and feel across different screens
 */

@Composable
fun ConversationsListScreen(
    onNewConversationClick: () -> Unit,
    onConversationClick: (chatId: String) -> Unit
) {

    val tabs = generateTabs()
    val selectedIndex = remember { mutableIntStateOf(1) }
    val pagerState = rememberPagerState(initialPage = 1, pageCount = { 3 })

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("WhatsApp")
                },
                actions = {
                    IconButton(
                        onClick = { /*some action*/ }
                    ) {
                        Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        bottomBar = {
            TabRow(selectedTabIndex = selectedIndex.intValue) {
                tabs.forEachIndexed { index, tab ->
                    Tab(
                        text = {
                            Text(
                                stringResource(tabs[index].title)
                            )
                        },
                        selected = index == selectedIndex.intValue,
                        onClick = {
                            // 1) update selectedIndex
                            selectedIndex.intValue = index
                            // 2) scroll pager to that page
                            /*                            coroutineScope.launch {
                                                            pagerState.animateScrollToPage(index)
                                                        }*/
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewConversationClick() },
                modifier = Modifier.background(
                    color =  MaterialTheme.colors. primarySurface,
                    shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                )
            }
        },

        content = { innerPadding ->

                HorizontalPager(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
                    state = pagerState,
                    pageContent = { index ->
                        when (index) {
                            0 -> {
                                // Status
                            }

                            1 -> {
                                ConversationList(
                                    conversations = generateFakeConversationList(),
                                )
                            }

                            2 -> {
                                // calls
                            }
                        }
                    })


            // Launched effect helps manage side effects like -
            // the launching tasks that have already been asynchronously completed.
            LaunchedEffect(selectedIndex.intValue) {
                Log.d("PagerTest", "About to scroll to page ${selectedIndex.intValue}")
                pagerState.animateScrollToPage(selectedIndex.intValue)
            }
        }
    )
}

fun generateFakeConversationList(): List<Conversation> {
    return listOf (
        Conversation(
            id = "1",
            name = "John Doe",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 8,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Jane Smith",
            message = "Heey, how have you been?",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://avatar.iran.liara.run/public/boy?username=Ash"
        ),
        Conversation(
            id = "1",
            name = "Michael Johnson",
            message = "catch up soon?",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://mdbcdn.b-cdn.net/img/new/avatars/2.webp"
        ),
        Conversation(
            id = "1",
            name = "Charlotte Helwig",
            message = "Yoooo",
            timestamp = "19:28",
            unreadCount = 1,
            avatarUrl = "https://mdbcdn.b-cdn.net/img/new/avatars/1.webp"
        ),
        Conversation(
            id = "1",
            name = "Emma Brown",
            message = "He called!!!!!",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://avatar.iran.liara.run/public/boy?username=Ash"
        ),
        Conversation(
            id = "1",
            name = "Lucas Smith",
            message = "What have you been up to?",
            timestamp = "19:28",
            unreadCount = 1,
            avatarUrl = "https://mdbcdn.b-cdn.net/img/new/avatars/8.webp"
        ),
        Conversation(
            id = "1",
            name = "Sophia Johnson",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 1,
            avatarUrl = "https://mdbcdn.b-cdn.net/img/Photos/Avatars/img (31).webp"
        ),
        Conversation(
            id = "1",
            name = "Olivia Brown",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 3,
            avatarUrl = "https://approachableai.com/wp-content/uploads/2022/12/SDv2.1-Example.jpg"
        ),
        Conversation(
            id = "1",
            name = "Liam Woodsworth",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Jake Smith",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 1,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Olivia Brown",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 3,
            avatarUrl = "https://approachableai.com/wp-content/uploads/2022/12/SDv2.1-Example.jpg"
        ),
        Conversation(
            id = "1",
            name = "Liam Woodsworth",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Jake Smith",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Olivia Brown",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 3,
            avatarUrl = "https://approachableai.com/wp-content/uploads/2022/12/SDv2.1-Example.jpg"
        ),
        Conversation(
            id = "1",
            name = "Liam Woodsworth",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        ),
        Conversation(
            id = "1",
            name = "Jake Smith",
            message = "What's Up",
            timestamp = "19:28",
            unreadCount = 0,
            avatarUrl = "https://www.gravatar.com/avatar/2c7d99fe281ecd3bcd65ab915bac6dd5?s=250"
        )
    )
}

data class ConversationsListTab(
    @StringRes val title: Int
)

fun generateTabs(): List<ConversationsListTab> {
    return listOf(
        ConversationsListTab(
            title = R.string.conversations_tab_status_title
        ),
        ConversationsListTab(
            title = R.string.conversations_tab_chats_title
        ),
        ConversationsListTab(
            title = R.string.conversations_tab_calls_title
        )
    )
}