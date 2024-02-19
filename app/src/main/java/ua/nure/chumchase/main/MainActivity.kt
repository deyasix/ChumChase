package ua.nure.chumchase.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ua.nure.chumchase.BottomNavigationBar
import ua.nure.chumchase.auth.presentation.AuthScreen
import ua.nure.chumchase.core.BottomNavItems
import ua.nure.chumchase.core.NavArguments
import ua.nure.chumchase.core.NavItems
import ua.nure.chumchase.core.presentation.components.ErrorScreen
import ua.nure.chumchase.core.presentation.components.LoadingScreen
import ua.nure.chumchase.feature.profile.presentation.ProfileScreen
import ua.nure.chumchase.core.presentation.theme.ChumChaseTheme
import ua.nure.chumchase.feature.chat.presentation.ChatScreen
import ua.nure.chumchase.feature.friends.presentation.FriendsScreen
import ua.nure.chumchase.feature.profile.presentation.ProfileViewModel
import ua.nure.chumchase.feature.settings.presentation.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChumChaseTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val isLoading by viewModel.isLoading.observeAsState()
    val isUserLogged by viewModel.isUserLogged.observeAsState()
    val error by viewModel.error.observeAsState()
    if (isLoading == true) {
        LoadingScreen()
    } else {
        if (error == null) {
            if (isUserLogged == true) AppScreen() else AuthScreen()
        } else ErrorScreen(onReload = viewModel::reload, errorResource = error)
    }
}

@Composable
fun AppScreen() {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavigationBar(navController) }) {
        NavHost(
            navController,
            startDestination = BottomNavItems.RECOMMENDATIONS.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavItems.RECOMMENDATIONS.route) {
                Text(stringResource(BottomNavItems.RECOMMENDATIONS.labelId))
            }
            composable(BottomNavItems.CHAT.route) {
                ChatScreen()
            }
            composable(BottomNavItems.FRIENDS.route) {
                FriendsScreen()
            }
            composable(
                "${BottomNavItems.PROFILE.route}/{${NavArguments.USER_ID}}",
                arguments = listOf(navArgument(NavArguments.USER_ID) { type = NavType.StringType })
            ) { navBackStackEntry ->
                val userId = navBackStackEntry.arguments?.getString(NavArguments.USER_ID)
                ProfileScreen(navController, viewModel = koinViewModel<ProfileViewModel>(
                    parameters = { parametersOf(userId) }
                ))
            }
            composable(
                BottomNavItems.PROFILE.route
            ) {
                ProfileScreen(navController, viewModel = koinViewModel<ProfileViewModel>(
                    parameters = { parametersOf(null) }
                ))
            }
            composable(NavItems.SETTINGS.route) {
                SettingsScreen()
            }
        }
    }
}
