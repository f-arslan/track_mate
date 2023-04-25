package com.example.track_mate.ui.screens.tablet.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.common.composables.AppNavRail
import com.example.track_mate.rememberAppState
import com.example.track_mate.ui.screens.graph.tabletGraph

@Composable
fun TabletScreen() {
    val appState = rememberAppState()
    Row {
        AppNavRail(appState::clearAndNavigate)
        NavHost(
            navController = appState.navController,
            startDestination = SEARCH_SCREEN_TABLET.route
        ) {
            tabletGraph()
        }
    }
}