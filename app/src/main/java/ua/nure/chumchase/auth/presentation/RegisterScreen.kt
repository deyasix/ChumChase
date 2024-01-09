package ua.nure.chumchase.auth.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.components.LabeledTextField

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val configuration = LocalConfiguration.current
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Row {
                Header(Modifier.weight(1f))
                RegisterForm(
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically), onNavigateToLogin
                )
            }
        } else {
            Column {
                Header(Modifier.weight(1f))
                RegisterForm(Modifier.weight(3f), onNavigateToLogin)
            }
        }
    }
}

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    val scrollState = rememberScrollState()
    val repeatedPassword by viewModel.repeatedPassword.observeAsState()
    val password by viewModel.password.observeAsState()
    Column(
        modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(state = scrollState, enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LabeledTextField(
            onChangeText = { viewModel.setLogin(it) },
            label = stringResource(R.string.login_label),
            initialText = viewModel.login.value
        )
        LabeledTextField(
            onChangeText = { viewModel.setEmail(it) },
            label = stringResource(R.string.email_label),
            initialText = viewModel.email.value,
            isEmail = true,
            isValidInput = { viewModel.verifyEmail() }
        )
        key(repeatedPassword) {
            LabeledTextField(
                onChangeText = { viewModel.setPassword(it) },
                label = stringResource(R.string.password_label),
                initialText = viewModel.password.value,
                isPassword = true,
                isValidInput = { viewModel.verifyPassword() }
            )
        }
        key(password) {
            LabeledTextField(
                onChangeText = { viewModel.setRepeatedPassword(it) },
                label = stringResource(R.string.repeat_password_label),
                initialText = viewModel.repeatedPassword.value,
                isPassword = true,
                isValidInput = { viewModel.verifyRepeatedPassword() }
            )
        }
        Button(onClick = { viewModel.register() }, enabled = viewModel.isRegistrationAvailable()) {
            Text(
                stringResource(R.string.register_button),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                stringResource(R.string.already_have_an_account),
                style = MaterialTheme.typography.bodyLarge
            )
            TextButton(onClick = onNavigateToLogin) {
                Text(
                    stringResource(R.string.login),
                    textDecoration = TextDecoration.Underline,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}