package com.example.track_mate.ui.screens.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.track_mate.ACTION_DETAIL_SCREEN_PHONE
import com.example.track_mate.ACTION_DETAIL_SCREEN_TABLET
import com.example.track_mate.ADD_ACTION_SCREEN_TABLET
import com.example.track_mate.ADD_INFORMATION_SCREEN_TABLET
import com.example.track_mate.DELETE_INFORMATION_SCREEN_TABLET
import com.example.track_mate.HOME_SCREEN_PHONE
import com.example.track_mate.HOME_SCREEN_TABLET
import com.example.track_mate.PRESENTATION_SCREEN_TABLET
import com.example.track_mate.SEARCH_SCREEN_PHONE
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.SETTING_SCREEN_TABLET
import com.example.track_mate.TrackMateAppState
import com.example.track_mate.model.Student
import com.example.track_mate.ui.screens.phone.home_screen.HomeScreenPhone
import com.example.track_mate.ui.screens.phone.search_screen.DetailScreenPhone
import com.example.track_mate.ui.screens.phone.search_screen.SearchScreenPhone
import com.example.track_mate.ui.screens.tablet.home_screen.HomeScreenTablet
import com.example.track_mate.ui.screens.tablet.search_screen.AddActionScreen
import com.example.track_mate.ui.screens.tablet.search_screen.DetailScreen
import com.example.track_mate.ui.screens.tablet.search_screen.SearchScreenTablet
import com.example.track_mate.ui.screens.tablet.setting_screen.AddInformationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.DeleteInformationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.SettingPresentationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.SettingScreenTablet


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
        AddActionScreen(
            navigateAndPopUp = { route, popUp ->
                appState.navigateAndPopUp(
                    route,
                    popUp
                )
            },
            student = student
        )
    }
}

fun NavGraphBuilder.settingScreenGraph(appState: TrackMateAppState) {
    composable(PRESENTATION_SCREEN_TABLET) {
        SettingPresentationScreen(
            navigate = { route -> appState.navigate(route) },
        )
    }
    composable(ADD_INFORMATION_SCREEN_TABLET) {
        AddInformationScreen()
    }
    composable(DELETE_INFORMATION_SCREEN_TABLET) {
        DeleteInformationScreen()
    }
}

fun NavGraphBuilder.phoneGraph(onItemClick: (String) -> Unit) {
    composable(SEARCH_SCREEN_PHONE.route) {
        SearchScreenPhone(onItemClick = onItemClick)
    }
    composable(HOME_SCREEN_PHONE.route) {
        HomeScreenPhone()
    }

    composable(route = "$ACTION_DETAIL_SCREEN_PHONE/{studentId}", arguments = listOf(
        navArgument("studentId") {
            type = NavType.StringType
        }
    )) {
        DetailScreenPhone(
            studentId = it.arguments?.getString("studentId") ?: ""
        )
    }
}