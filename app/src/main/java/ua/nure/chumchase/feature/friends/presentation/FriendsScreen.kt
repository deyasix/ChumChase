package ua.nure.chumchase.feature.friends.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.core.components.LoadingScreen
import ua.nure.chumchase.feature.friends.domain.FriendDTO
import ua.nure.chumchase.feature.friends.presentation.components.SearchBar
import ua.nure.chumchase.feature.profile.presentation.components.ProfilePhoto

@Composable
fun FriendsScreen(viewModel: FriendsViewModel = koinViewModel()) {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = Modifier.fillMaxSize()) {
        val isLoading by viewModel.isLoading.observeAsState()
        if (isLoading == true) {
            LoadingScreen()
        } else {
            val friends by viewModel.friends.observeAsState()
            val modifier = Modifier.padding(dimensionResource(R.dimen.base_horizontal_padding))
            val isSearching by viewModel.isSearching.observeAsState()
            Column {
                SearchBar(modifier.fillMaxWidth(), viewModel::search, isSearching ?: false)
                Box(modifier.fillMaxSize()) {
                    FriendsList(
                        Modifier.align(Alignment.Center),
                        friends ?: listOf()
                    )
                }
            }
        }
    }
}


@Composable
fun FriendsList(modifier: Modifier = Modifier, friends: List<FriendDTO>) {
    if (friends.isEmpty()) Text(
        stringResource(R.string.empty_friends_list_placeholder),
        modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface
    )
    else LazyColumn(modifier) {
        items(friends) {
            Friend(friend = it, modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun Friend(modifier: Modifier = Modifier, friend: FriendDTO) {
    Row(modifier) {
        ProfilePhoto(modifier = Modifier.size(40.dp), photoUrl = friend.photoUrl)
        Text(friend.login, modifier = Modifier.padding(4.dp))
    }
}
