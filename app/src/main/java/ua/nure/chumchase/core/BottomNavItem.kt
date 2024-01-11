package ua.nure.chumchase.core

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import ua.nure.chumchase.R

sealed class BottomNavItem(val route: String, val icon: ImageVector, @StringRes val labelId: Int) {
    object Recommendations :
        BottomNavItem("recommendations", Icons.Rounded.Public, R.string.recommendations_button)

    object Chat : BottomNavItem("chat", Icons.Rounded.Chat, R.string.chat_button)
    object Friends : BottomNavItem("friends", Icons.Rounded.People, R.string.friends_button)
    object Profile : BottomNavItem("profile", Icons.Rounded.AccountCircle, R.string.profile_button)

    companion object {
        val values = listOf(Recommendations, Chat, Friends, Profile)
    }
}