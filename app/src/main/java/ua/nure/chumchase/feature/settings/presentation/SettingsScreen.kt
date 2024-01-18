package ua.nure.chumchase.feature.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.nure.chumchase.R
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme
import ua.nure.chumchase.feature.profile.presentation.Tags

@Composable
fun SettingsScreen() {
    Surface(color = MaterialTheme.colorScheme.surface) {
        Column(modifier = Modifier.padding(16.dp)) {
            SettingsElement(
                title = stringResource(R.string.settings_login_title),
                content = { Text("login", style = MaterialTheme.typography.bodyMedium) }, onEdit = {}
            )
            SettingsElement(
                title = stringResource(R.string.settings_email_title),
                content = {
                    Text(
                        "angerozka@gmail.com",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }, onEdit = {}
            )
            SettingsElement(
                title = stringResource(R.string.settings_interests_title),
                content = { Tags(labels = listOf("Music", "Sport", "Programming", "TV", "Sport", "Sport"), horizontalArrangement = Arrangement.Start) },
                onEdit = {}
            )

        }
    }
}

@Composable
@Preview
fun SettingsScreenPreview() {
    ChumChaseTheme {
        SettingsScreen()
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
                Icon(Icons.Rounded.Edit, "")
            }
        }
    }
}
