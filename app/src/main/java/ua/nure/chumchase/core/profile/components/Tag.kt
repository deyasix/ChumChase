package ua.nure.chumchase.core.profile.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Tag(modifier: Modifier = Modifier, label: String) {
    SuggestionChip(
        modifier = modifier,
        onClick = { },
        label = { Text(label, style = MaterialTheme.typography.bodyLarge) })
}