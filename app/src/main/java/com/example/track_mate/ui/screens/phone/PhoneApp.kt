package com.example.track_mate.ui.screens.phone

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.example.track_mate.ACTION_DETAIL_SCREEN_PHONE
import com.example.track_mate.HOME_SCREEN_PHONE
import com.example.track_mate.common.composables.BottomBarPhone
import com.example.track_mate.rememberAppState
import com.example.track_mate.ui.screens.graph.phoneGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneApp() {
    val appState = rememberAppState()
    Scaffold(bottomBar = {
        BottomBarPhone(
            navController = appState.navController
        )
    }) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = appState.navController,
            startDestination = HOME_SCREEN_PHONE.route
        ) {
            phoneGraph(appState)
        }
    }
}

