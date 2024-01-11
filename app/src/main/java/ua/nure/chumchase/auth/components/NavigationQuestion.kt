package ua.nure.chumchase.auth.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun NavigationQuestion(
    modifier: Modifier = Modifier,
    @StringRes questionText: Int,
    @StringRes buttonText: Int,
    onNavigate: () -> Unit
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            stringResource(questionText),
            style = MaterialTheme.typography.bodyLarge
        )
        TextButton(onClick = onNavigate) {
            Text(
                stringResource(buttonText),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}