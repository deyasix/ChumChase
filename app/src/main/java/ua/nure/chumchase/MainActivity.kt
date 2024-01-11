package ua.nure.chumchase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.nure.chumchase.auth.presentation.LoginScreen
import ua.nure.chumchase.auth.presentation.RegisterScreen
import ua.nure.chumchase.core.BottomNavItem
import ua.nure.chumchase.core.Screen
import ua.nure.chumchase.core.theme.ChumChaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChumChaseTheme {
                AuthScreen()
            }
        }
    }
}

@Composable
fun AuthScreen() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(onNavigateToRegister = { navController.navigate(Screen.Registration.route) },
                onNavigateToMain = { navController.navigate(Screen.Main.route) })
        }
        composable(Screen.Registration.route) {
            RegisterScreen(onNavigateToLogin = {
                navController.navigate(
                    Screen.Login.route
                )
            }, onNavigateToMain = { navController.navigate(Screen.Main.route) })
        }
        composable(Screen.Main.route) {
            AppScreen()
        }
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        NavHost(
            navController,
            startDestination = BottomNavItem.Recommendations.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavItem.Recommendations.route) {
                Text(stringResource(BottomNavItem.Recommendations.labelId))
            }
            composable(BottomNavItem.Chat.route) {
                Text(stringResource(BottomNavItem.Chat.labelId))
            }
            composable(BottomNavItem.Friends.route) {
                Text(stringResource(BottomNavItem.Friends.labelId))
            }
            composable(BottomNavItem.Profile.route) {
                Text(stringResource(BottomNavItem.Profile.labelId))
            }
        }
    }
}
