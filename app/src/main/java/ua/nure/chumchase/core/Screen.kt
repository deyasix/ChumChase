package ua.nure.chumchase.core

import androidx.annotation.StringRes
import ua.nure.chumchase.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Login : Screen("login", R.string.login_nav)
    object Registration : Screen("registration", R.string.registration_nav)
}