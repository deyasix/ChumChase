package ua.nure.chumchase.auth.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import ua.nure.chumchase.R
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme
import ua.nure.chumchase.core.ui.theme.LogoFamily

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(dimensionResource(R.dimen.header_padding))
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                style = TextStyle(
                    fontFamily = LogoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 48.sp
                ), text = stringResource(R.string.app_name), modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .heightIn(max = 52.dp), color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(R.string.app_tagline),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
@Preview
private fun HeaderPreview() {
    ChumChaseTheme {
        Header()
    }
}