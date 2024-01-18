package ua.nure.chumchase.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import ua.nure.chumchase.core.NavItems
import ua.nure.chumchase.main.AppScreen

@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavItems.LOGIN.route) {
        composable(NavItems.LOGIN.route) {
            LoginScreen(onNavigateToRegister = { navController.navigate(NavItems.REGISTRATION.route) },
                onNavigateToMain = { navController.navigate(NavItems.MAIN.route) })
        }
        composable(NavItems.REGISTRATION.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(
                    NavItems.LOGIN.route
                )
            }, onNavigateToMain = { navController.navigate(NavItems.MAIN.route) })
        }
        composable(NavItems.MAIN.route) {
            AppScreen()
        }
    }
}