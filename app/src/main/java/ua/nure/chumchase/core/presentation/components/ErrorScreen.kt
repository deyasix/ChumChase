package ua.nure.chumchase.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.nure.chumchase.R

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier, onReload: () -> Unit, errorResource: Int?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Rounded.ErrorOutline,
            stringResource(R.string.error_icon_description),
            Modifier.size(80.dp)
        )
        Text(
            stringResource(errorResource ?: R.string.failed_operation),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.base_horizontal_padding))
        )
        Button(onClick = onReload) {
            Text(
                stringResource(R.string.button_try_again),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}