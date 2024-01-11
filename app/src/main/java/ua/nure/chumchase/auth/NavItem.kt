package ua.nure.chumchase.auth

import androidx.annotation.StringRes
import ua.nure.chumchase.R

sealed class NavItem(val route: String, @StringRes val resourceId: Int) {
    object Login : NavItem("login", R.string.login_nav)
    object Registration : NavItem("registration", R.string.registration_nav)
    object Main : NavItem("main", R.string.main_nav)
}