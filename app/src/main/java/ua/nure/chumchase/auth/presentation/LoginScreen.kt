package ua.nure.chumchase.auth.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import org.koin.java.KoinJavaComponent.get
import ua.nure.chumchase.R
import ua.nure.chumchase.components.LabeledTextField
import ua.nure.chumchase.ui.theme.ChumChaseTheme

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = get(LoginViewModel::class.java)) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            LoginHeader(
                Modifier
                    .background(color = MaterialTheme.colorScheme.primary)
                    .weight(1f)
            )
            LoginForm(
                Modifier
                    .weight(3f)
                    .padding(16.dp), loginViewModel
            )
        }
    }
}

@Composable
@Preview
fun LoginScreenPreview() {
    ChumChaseTheme {
        LoginScreen()
    }
}

@Composable
fun LoginHeader(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxWidth()) {
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

@Composable
fun LoginForm(modifier: Modifier = Modifier, viewModel: LoginViewModel) {
    Column(
        modifier.fillMaxWidth(),
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
            initialText = viewModel.password.value
        )
        Button(onClick = {}) {
            Text(stringResource(R.string.login_button), style = MaterialTheme.typography.bodyLarge)
        }
    }
}