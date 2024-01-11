package ua.nure.chumchase.auth.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.*
import androidx.compose.ui.text.style.TextDecoration
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.core.components.Header
import ua.nure.chumchase.core.components.LabeledTextField

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: RegisterViewModel = koinViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        ResultResponder(viewModel, onNavigateToMain, snackbarHostState)
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { padding ->
            Surface(Modifier.fillMaxSize()) {
                val configuration = LocalConfiguration.current
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    Row(Modifier.padding(padding)) {
                        Header(Modifier.weight(1f))
                        RegisterForm(
                            Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically), onNavigateToLogin
                        )
                    }
                } else {
                    Column(Modifier.padding(padding)) {
                        Header(Modifier.weight(1f))
                        RegisterForm(Modifier.weight(3f), onNavigateToLogin)
                    }
                }
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
    val isLoading by viewModel.isLoading.observeAsState()
    val padding = dimensionResource(R.dimen.login_register_form_padding)
    Column(
        modifier
            .padding(padding)
            .fillMaxWidth()
            .verticalScroll(state = scrollState, enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        LabeledTextField(
            onChangeText = viewModel::setLogin,
            label = stringResource(R.string.login_label),
            initialText = viewModel.login.value
        )
        LabeledTextField(
            onChangeText = viewModel::setEmail,
            label = stringResource(R.string.email_label),
            initialText = viewModel.email.value,
            isEmail = true,
            isValidInput = { viewModel.verifyEmail() }
        )
        key(repeatedPassword) {
            LabeledTextField(
                onChangeText = viewModel::setPassword,
                label = stringResource(R.string.password_label),
                initialText = viewModel.password.value,
                isPassword = true,
                isValidInput = { viewModel.verifyPassword() }
            )
        }
        key(password) {
            LabeledTextField(
                onChangeText = viewModel::setRepeatedPassword,
                label = stringResource(R.string.repeat_password_label),
                initialText = viewModel.repeatedPassword.value,
                isPassword = true,
                isValidInput = { viewModel.verifyRepeatedPassword() }
            )
        }
        Button(onClick = viewModel::register, enabled = viewModel.isRegistrationAvailable()) {
            Text(
                stringResource(R.string.register_button),
                style = MaterialTheme.typography.bodyLarge
            )
        }
        if (isLoading == true) {
            LinearProgressIndicator()
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