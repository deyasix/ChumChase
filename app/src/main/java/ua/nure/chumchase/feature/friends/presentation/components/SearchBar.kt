package ua.nure.chumchase.feature.friends.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ua.nure.chumchase.R

@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(modifier = modifier, value = text, onValueChange = {
        text = it
        onSearch(it)
    }, placeholder = {
        Text(stringResource(R.string.search_placeholder))
    }, leadingIcon = {
        Icon(Icons.Rounded.Search, stringResource(R.string.search_icon_description))
    })
}