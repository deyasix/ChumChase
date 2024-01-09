package ua.nure.chumchase.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.*
import ua.nure.chumchase.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary)) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.spicy_rice, FontWeight.Normal)),
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