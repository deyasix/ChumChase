package ua.nure.chumchase.auth.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.components.LabeledTextField

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val configuration = LocalConfiguration.current
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Row {
                Header(Modifier.weight(1f))
                LoginForm(Modifier.weight(1f), onNavigateToRegister)
            }
        } else {
            Column {
                Header(Modifier.weight(1f))
                LoginForm(Modifier.weight(3f), onNavigateToRegister)
            }
        }

    }
}

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    Column(
        modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LabeledTextField(
            onChangeText = { viewModel.setLogin(it) },
            label = stringResource(R.string.login_label),
            initialText = viewModel.login.value
        )
        LabeledTextField(
            onChangeText = { viewModel.setPassword(it) },
            label = stringResource(R.string.password_label),
            initialText = viewModel.password.value,
            isPassword = true
        )
        Button(onClick = { viewModel.login() }) {
            Text(stringResource(R.string.login_button), style = MaterialTheme.typography.bodyLarge)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.dont_have_an_account),
                style = MaterialTheme.typography.bodyLarge
            )
            TextButton(onClick = onNavigateToRegister) {
                Text(
                    stringResource(R.string.register),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}