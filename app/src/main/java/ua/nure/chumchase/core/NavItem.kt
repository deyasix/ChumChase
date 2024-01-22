package ua.nure.chumchase.core

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Public
import androidx.compose.ui.graphics.vector.ImageVector
import ua.nure.chumchase.R

interface Navigational {
    val route: String

    @get:StringRes
    val labelId: Int
}

enum class BottomNavItems(
    override val route: String,
    override val labelId: Int,
    val icon: ImageVector
) :
    Navigational {
    RECOMMENDATIONS("recommendations", R.string.recommendations_button, Icons.Rounded.Public),
    CHAT("chat", R.string.chat_button, Icons.Rounded.Chat),
    FRIENDS("friends", R.string.friends_button, Icons.Rounded.People),
    PROFILE("profile", R.string.profile_button, Icons.Rounded.AccountCircle),
}

enum class NavItems(override val route: String, override val labelId: Int) : Navigational {
    LOGIN("login", R.string.login_nav),
    REGISTRATION("registration", R.string.registration_nav),
    MAIN("main", R.string.main_nav),
    SETTINGS("settings", R.string.settings_nav)
}
