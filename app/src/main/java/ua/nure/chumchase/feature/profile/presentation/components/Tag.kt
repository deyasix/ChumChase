package ua.nure.chumchase.feature.profile.presentation.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Tag(modifier: Modifier = Modifier, label: String, onClick: (String) -> Unit) {
    AssistChip(
        modifier = modifier,
        onClick = { onClick(label) },
        label = { Text(label, style = MaterialTheme.typography.bodyLarge) })
}
