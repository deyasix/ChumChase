package ua.nure.chumchase.feature.friends.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.nure.chumchase.R
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme

@Composable
fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit, isSearching: Boolean = false) {
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
    }, trailingIcon = {
        if (isSearching) CircularProgressIndicator(Modifier.size(24.dp))
    })
}

@Composable
@Preview
fun SearchBarPreview() {
    ChumChaseTheme {
        SearchBar(onSearch = {}, isSearching = true)
    }
}