package com.example.track_mate.ui.screens.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.track_mate.ACTION_DETAIL_SCREEN_TABLET
import com.example.track_mate.ADD_ACTION_SCREEN_TABLET
import com.example.track_mate.HOME_SCREEN_TABLET
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.SETTING_SCREEN_TABLET
import com.example.track_mate.TrackMateAppState
import com.example.track_mate.model.Student
import com.example.track_mate.ui.screens.tablet.screens.home_screen.HomeScreenTablet
import com.example.track_mate.ui.screens.tablet.screens.search_screen.AddActionScreen
import com.example.track_mate.ui.screens.tablet.screens.search_screen.DetailScreen
import com.example.track_mate.ui.screens.tablet.screens.search_screen.SearchScreenTablet
import com.example.track_mate.ui.screens.tablet.screens.setting_screen.SettingScreenTablet


fun NavGraphBuilder.tabletGraph() {
    composable(SEARCH_SCREEN_TABLET.route) {
        SearchScreenTablet()
    }
    composable(HOME_SCREEN_TABLET.route) {
        HomeScreenTablet()
    }
    composable(SETTING_SCREEN_TABLET.route) {
        SettingScreenTablet()
    }
}

fun NavGraphBuilder.searchScreenGraph(appState: TrackMateAppState, student: Student) {
    composable(ACTION_DETAIL_SCREEN_TABLET) {
        DetailScreen(student = student)
    }

    composable(ADD_ACTION_SCREEN_TABLET) {
        AddActionScreen(navigateAndPopUp = { route, popUp ->
            appState.navigateAndPopUp(route, popUp)
        }, student = student)
    }
}