package ua.nure.chumchase.feature.chat.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.core.components.LoadingScreen

@Composable
fun ChatScreen(viewModel: ChatViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.observeAsState()
    ChatTheme(isInDarkMode = false) {
        if (isLoading == true) {
            LoadingScreen()
        } else {
            ChannelsScreen(
                isShowingHeader = true,
                title = "Chat",
                onItemClick = {},
                onBackPressed = { }
            )
        }
    }
}