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
import ua.nure.chumchase.ui.theme.ChumChaseTheme

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
        composable("login") {
            LoginScreen(onNavigateToRegister = { navController.navigate("register") })
        }
        composable("register") { RegisterScreen(onNavigateToLogin = { navController.navigate("login") }) }
    }
}