package ua.nure.chumchase.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import ua.nure.chumchase.AppScreen
import ua.nure.chumchase.auth.NavItem

@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavItem.Login.route) {
        composable(NavItem.Login.route) {
            LoginScreen(onNavigateToRegister = { navController.navigate(NavItem.Registration.route) },
                onNavigateToMain = { navController.navigate(NavItem.Main.route) })
        }
        composable(NavItem.Registration.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(
                    NavItem.Login.route
                )
            }, onNavigateToMain = { navController.navigate(NavItem.Main.route) })
        }
        composable(NavItem.Main.route) {
            AppScreen()
        }
    }
}