package ua.nure.chumchase.feature.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.core.components.LoadingScreen
import ua.nure.chumchase.feature.profile.presentation.Tags

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.observeAsState()
    if (isLoading == true) LoadingScreen()
    else Surface(color = MaterialTheme.colorScheme.surface) {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.settings_horizontal_padding))) {
            val login by viewModel.login.observeAsState()
            val email by viewModel.email.observeAsState()
            val tags by viewModel.tags.observeAsState()
            SettingsElement(
                title = stringResource(R.string.settings_login_title),
                content = { Text(login ?: "", style = MaterialTheme.typography.bodyMedium) },
                onEdit = {}
            )
            SettingsElement(
                title = stringResource(R.string.settings_email_title),
                content = {
                    Text(
                        email ?: "",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }, onEdit = {}
            )
            SettingsElement(
                title = stringResource(R.string.settings_interests_title),
                content = {
                    Tags(
                        labels = tags ?: listOf(),
                        horizontalArrangement = Arrangement.Start
                    )
                },
                onEdit = {}
            )

        }
    }
}

@Composable
fun SettingsElement(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable () -> Unit,
    onEdit: () -> Unit
) {
    Column(
        modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(8f)) {
                content()
            }
            IconButton(onClick = onEdit, modifier = Modifier.weight(1f)) {
                Icon(Icons.Rounded.Edit, stringResource(R.string.edit_button_description))
            }
        }
    }
}
