package ua.nure.chumchase

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.*
import ua.nure.chumchase.core.BottomNavItem
import ua.nure.chumchase.core.theme.ChumChaseTheme

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        BottomNavItem.values.forEach { item ->
            val isSelected = currentRoute == item.route
            val color = getColor(isSelected)
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = stringResource(item.labelId),
                        tint = color
                    )
                },
                label = {
                    Text(
                        stringResource(item.labelId),
                        style = MaterialTheme.typography.bodySmall,
                        color = color
                    )
                }
            )
        }
    }
}

@Composable
private fun getColor(isSelected: Boolean): Color {
    with(MaterialTheme.colorScheme) {
        return if (isSelected) secondary else primary
    }
}

@Composable
@Preview
private fun BottomNavigationBarPreview() {
    ChumChaseTheme {
        BottomNavigationBar(rememberNavController())
    }
}
