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
import ua.nure.chumchase.core.BottomNavItem
import ua.nure.chumchase.auth.presentation.AuthScreen
import ua.nure.chumchase.core.profile.presentation.ProfileScreen
import ua.nure.chumchase.core.ui.theme.ChumChaseTheme

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
                ProfileScreen()
            }
        }
    }
}
