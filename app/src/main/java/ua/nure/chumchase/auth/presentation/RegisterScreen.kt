package ua.nure.chumchase.auth.presentation

import androidx.compose.runtime.Composable
import org.koin.java.KoinJavaComponent

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel = KoinJavaComponent.get(RegisterViewModel::class.java),
    onNavigateToLogin: () -> Unit
) {

}