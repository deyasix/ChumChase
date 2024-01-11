package ua.nure.chumchase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.nure.chumchase.auth.presentation.LoginScreen
import ua.nure.chumchase.auth.presentation.RegisterScreen
import ua.nure.chumchase.core.Screen
import ua.nure.chumchase.core.theme.ChumChaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChumChaseTheme {
                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {
        composable(Screen.Login.route) {
            LoginScreen(onNavigateToRegister = { navController.navigate(Screen.Registration.route) })
        }
        composable(Screen.Registration.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(
                    Screen.Login.route
                )
            })
        }
    }
}