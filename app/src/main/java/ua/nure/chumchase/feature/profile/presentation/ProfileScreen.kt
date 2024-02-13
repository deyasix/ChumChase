package ua.nure.chumchase.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.core.presentation.components.LoadingScreen
import ua.nure.chumchase.core.presentation.components.ResultResponder
import ua.nure.chumchase.feature.profile.presentation.components.*

@Composable
fun ProfileScreen(
    onNavigateToSettings: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel()
) {
    val modifier = Modifier.padding(dimensionResource(R.dimen.base_horizontal_padding))
    val snackBarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Surface(color = MaterialTheme.colorScheme.surface, modifier = Modifier.padding(it)) {
            val isLoading by viewModel.isLoading.observeAsState()
            if (isLoading == true) LoadingScreen()
            else {
                val user by viewModel.userInfo.observeAsState()
                val isCommentSending by viewModel.isCommentSending.observeAsState()
                ResultResponder(viewModel, snackBarHostState)
                LazyColumn {
                    item {
                        ProfileHeader(modifier = modifier, onNavigateToSettings)
                        CommentField(
                            modifier = modifier,
                            initialText = viewModel.currentComment.value,
                            placeholder = stringResource(R.string.comment_field_placeholder),
                            onChangeText = viewModel::setCurrentComment,
                            onSendComment = viewModel::sendComment,
                            isCommentSending = isCommentSending
                        )
                    }
                    user?.comments?.let { comments ->
                        items(comments) { comment ->
                            Comment(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.surface)
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = dimensionResource(R.dimen.profile_vertical_padding),
                                        horizontal = dimensionResource(R.dimen.base_horizontal_padding)
                                    ),
                                photoUrl = comment.senderPhotoUrl,
                                login = comment.senderLogin,
                                text = comment.text,
                                date = comment.date
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier, onNavigateToSettings: () -> Unit) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SettingsButton(Modifier.align(Alignment.End), onNavigateToSettings)
        ProfileInfo()
    }
}

@Composable
fun ProfileInfo(modifier: Modifier = Modifier, viewModel: ProfileViewModel = koinViewModel()) {
    val user by viewModel.userInfo.observeAsState()
    user?.let {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            ProfilePhoto(
                modifier = Modifier.size(dimensionResource(R.dimen.profile_photo_size)),
                photoUrl = it.photoUrl
            )
            Text(
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.profile_vertical_padding)),
                text = it.login,
                style = MaterialTheme.typography.bodyLarge
            )
            Tags(labels = it.tags)
        }
    }
}

@Composable
fun SettingsButton(modifier: Modifier = Modifier, onNavigateToSettings: () -> Unit) {
    IconButton(modifier = modifier, onClick = { onNavigateToSettings() }) {
        Icon(Icons.Rounded.Settings, stringResource(R.string.settings_button_description))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    modifier: Modifier = Modifier,
    labels: List<String>,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    FlowRow(modifier, horizontalArrangement = horizontalArrangement) {
        labels.forEach {
            Tag(
                modifier.padding(horizontal = dimensionResource(R.dimen.tag_horizontal_padding)),
                label = it,
                onClick = {}
            )
        }
    }
}