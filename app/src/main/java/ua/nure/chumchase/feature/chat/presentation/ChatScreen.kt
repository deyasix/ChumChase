package ua.nure.chumchase.feature.chat.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.ui.theme.StreamColors
import io.getstream.chat.android.compose.ui.theme.StreamTypography
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.core.presentation.components.LoadingScreen
import ua.nure.chumchase.core.presentation.theme.ManropeFontFamily

@Composable
fun ChatScreen(viewModel: ChatViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.observeAsState()
    ChatTheme(
        isInDarkMode = false,
        colors = StreamColors.defaultColors()
            .copy(
                appBackground = MaterialTheme.colorScheme.surface,
                primaryAccent = MaterialTheme.colorScheme.primary,
                disabled = MaterialTheme.colorScheme.onSurface,
                textLowEmphasis = MaterialTheme.colorScheme.onSurface
            ),
        typography = StreamTypography.defaultTypography(ManropeFontFamily)
    ) {
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