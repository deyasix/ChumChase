package ua.nure.chumchase.feature.profile.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme

@Composable
fun Tag(modifier: Modifier = Modifier, label: String, onClick: (String) -> Unit) {
    AssistChip(
        modifier = modifier,
        onClick = { onClick(label) },
        label = { Text(label, style = MaterialTheme.typography.bodyLarge) })
}
