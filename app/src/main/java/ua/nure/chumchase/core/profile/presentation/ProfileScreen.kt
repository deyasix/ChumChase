package ua.nure.chumchase.core.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.core.components.LoadingScreen
import ua.nure.chumchase.core.profile.components.CommentField
import ua.nure.chumchase.core.profile.components.Tag
import ua.nure.chumchase.core.profile.components.Comment
import ua.nure.chumchase.core.profile.components.ProfilePhoto
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val modifier = Modifier.padding(16.dp)
    Surface(color = MaterialTheme.colorScheme.surface) {
        val isLoading by viewModel.isLoading.observeAsState()
        if (isLoading == true) LoadingScreen()
        else {
            val comments by viewModel.comments.observeAsState()
            LazyColumn {
                item {
                    ProfileHeader(modifier = modifier)
                    CommentField(
                        modifier = modifier,
                        initialText = viewModel.currentComment.value,
                        placeholder = stringResource(R.string.comment_field_placeholder),
                        onChangeText = viewModel::setCurrentComment,
                        onSendComment = viewModel::sendComment
                    )
                }
                comments?.let {
                    items(it) { comment ->
                        Comment(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.surface)
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            comment = comment
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        SettingsButton(Modifier.align(Alignment.End))
        ProfileInfo()
    }
}

@Composable
fun ProfileInfo(modifier: Modifier = Modifier, viewModel: ProfileViewModel = koinViewModel()) {
    val photoUrl by viewModel.photoUrl.observeAsState()
    val login by viewModel.login.observeAsState()
    val tags by viewModel.tags.observeAsState()
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        ProfilePhoto(photoUrl = photoUrl)
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = login ?: "",
            style = MaterialTheme.typography.bodyLarge
        )
        Tags(labels = tags ?: listOf())
    }
}

@Composable
fun SettingsButton(modifier: Modifier = Modifier) {
    IconButton(modifier = modifier, onClick = { }) {
        Icon(Icons.Rounded.Settings, stringResource(R.string.settings_button_description))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(modifier: Modifier = Modifier, labels: List<String>) {
    FlowRow(modifier, horizontalArrangement = Arrangement.Center) {
        labels.forEach {
            Tag(modifier.padding(horizontal = 2.dp), label = it)
        }
    }
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ChumChaseTheme {
        ProfileScreen()
    }
}