package com.example.track_mate.common.composables

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.track_mate.HOME_SCREEN_PHONE
import com.example.track_mate.SEARCH_SCREEN_PHONE

@Composable
fun BottomBarPhone(
    navController: NavHostController
) {
    val destinations = listOf(
        HOME_SCREEN_PHONE,
        SEARCH_SCREEN_PHONE
    )
    BottomAppBar{
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        destinations.forEach { destination ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == destination.route } == true
            NavigationBarItem(selected = selected,
                onClick = {
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    val icon = if (selected) destination.iconFilled else destination.iconOutline
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = destination.route.removeSuffix("ScreenPhone"))
                }
            )
        }
    }
}