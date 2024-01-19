package ua.nure.chumchase.auth.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ua.nure.chumchase.R
import ua.nure.chumchase.auth.presentation.components.*

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val snackBarHostState = remember { SnackbarHostState() }
    ResultResponder(viewModel, onNavigateToMain, snackBarHostState)
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        Surface(Modifier.fillMaxSize()) {
            val configuration = LocalConfiguration.current
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Row(Modifier.padding(it)) {
                    Header(
                        Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    )
                    LoginForm(Modifier.weight(1f), onNavigateToRegister)
                }
            } else {
                Column(Modifier.padding(it)) {
                    Header(Modifier.weight(1f))
                    LoginForm(Modifier.weight(3f), onNavigateToRegister)
                }
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
    val login by viewModel.login.observeAsState()
    val password by viewModel.password.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState()
    val padding = dimensionResource(R.dimen.login_register_form_padding)
    Column(
        modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(padding)
    ) {
        LabeledTextField(
            onChangeText = viewModel::setLogin,
            label = stringResource(R.string.login_label),
            initialText = viewModel.login.value
        )
        LabeledTextField(
            onChangeText = viewModel::setPassword,
            label = stringResource(R.string.password_label),
            initialText = viewModel.password.value,
            isPassword = true
        )
        key(login, password) {
            Button(onClick = viewModel::login, enabled = viewModel.isLoginAvailable()) {
                Text(
                    stringResource(R.string.login_button),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        if (isLoading == true) {
            LinearProgressIndicator()
        }
        NavigationQuestion(
            questionText = R.string.dont_have_an_account,
            buttonText = R.string.register,
            onNavigate = onNavigateToRegister
        )
    }
}