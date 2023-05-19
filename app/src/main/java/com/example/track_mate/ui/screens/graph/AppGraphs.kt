package com.example.track_mate.ui.screens.graph

import androidx.compose.runtime.collectAsState
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
import com.example.track_mate.PHONE_APP
import com.example.track_mate.PRESENTATION_SCREEN_TABLET
import com.example.track_mate.SEARCH_SCREEN_PHONE
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.SETTING_SCREEN_TABLET
import com.example.track_mate.SIGN_IN_SCREEN_PHONE
import com.example.track_mate.SIGN_IN_SCREEN_TABLET
import com.example.track_mate.SIGN_UP_SCREEN_PHONE
import com.example.track_mate.SIGN_UP_SCREEN_TABLET
import com.example.track_mate.SPLASH_SCREEN
import com.example.track_mate.TABLET_APP
import com.example.track_mate.TrackMateAppState
import com.example.track_mate.core.model.Student
import com.example.track_mate.ui.screens.SplashScreenProvider
import com.example.track_mate.ui.screens.phone.PhoneApp
import com.example.track_mate.ui.screens.phone.home_screen.HomeScreenPhone
import com.example.track_mate.ui.screens.phone.search_screen.DetailScreenPhone
import com.example.track_mate.ui.screens.phone.search_screen.SearchScreenPhone
import com.example.track_mate.ui.screens.phone.sign_in_screen.SignInScreenPhoneProvider
import com.example.track_mate.ui.screens.phone.sign_up_screen.SignUpScreenPhoneProvider
import com.example.track_mate.ui.screens.tablet.TabletApp
import com.example.track_mate.ui.screens.tablet.home_screen.HomeScreenTablet
import com.example.track_mate.ui.screens.tablet.search_screen.AddActionScreen
import com.example.track_mate.ui.screens.tablet.search_screen.DetailScreen
import com.example.track_mate.ui.screens.tablet.search_screen.SearchScreenTablet
import com.example.track_mate.ui.screens.tablet.setting_screen.AddInformationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.DeleteInformationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.SettingPresentationScreen
import com.example.track_mate.ui.screens.tablet.setting_screen.SettingScreenTablet
import com.example.track_mate.ui.screens.tablet.sign_in_screen.SignInScreenProvider
import com.example.track_mate.ui.screens.tablet.sign_up_screen.SignUpScreenProvider
import com.example.track_mate.ui.screens.view_models.MainViewModel


fun NavGraphBuilder.tabletGraph(appState: TrackMateAppState) {
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

fun NavGraphBuilder.topLevelTabletGraph(appState: TrackMateAppState, viewModel: MainViewModel) {
    composable(SPLASH_SCREEN) {
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        val nextDestination = if (isUserSignedOut) {
            SIGN_IN_SCREEN_TABLET
        } else {
            if (viewModel.isEmailVerified) {
                TABLET_APP
            } else {
                SIGN_UP_SCREEN_TABLET // TODO: VERIFY_EMAIL_SCREEN
            }
        }
        SplashScreenProvider(popUpAndNavigate = {
            appState.navigateAndPopUp(
                nextDestination, SPLASH_SCREEN
            )
        })
    }

    composable(SIGN_UP_SCREEN_TABLET) {
        SignUpScreenProvider(openAndPopUp = {
            appState.navigateAndPopUp(SIGN_IN_SCREEN_TABLET, SIGN_UP_SCREEN_TABLET)
        })
    }
    composable(SIGN_IN_SCREEN_TABLET) {
        SignInScreenProvider()
    }

    composable(TABLET_APP) {
        TabletApp()
    }


}

fun NavGraphBuilder.topLevelPhoneGraph(appState: TrackMateAppState) {
    composable(SPLASH_SCREEN) {
        SplashScreenProvider(popUpAndNavigate = {
            appState.navigateAndPopUp(
                SIGN_UP_SCREEN_PHONE, SPLASH_SCREEN
            )
        })
    }
    composable(SIGN_IN_SCREEN_PHONE) {
        SignInScreenPhoneProvider()
    }

    composable(SIGN_UP_SCREEN_PHONE) {
        SignUpScreenPhoneProvider(openAndPopUp = {
            appState.navigateAndPopUp(SIGN_IN_SCREEN_PHONE, SIGN_UP_SCREEN_PHONE)
        })
    }

    composable(PHONE_APP) {
        PhoneApp()
    }
}

fun NavGraphBuilder.searchScreenGraph(
    appState: TrackMateAppState, student: Student
) {
    composable(ACTION_DETAIL_SCREEN_TABLET) {
        DetailScreen(student = student)
    }

    composable(ADD_ACTION_SCREEN_TABLET) {
        AddActionScreen(
            popUp = { appState.popUp() }, student = student
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
        AddInformationScreen(popUp = { appState.popUp() })
    }
    composable(DELETE_INFORMATION_SCREEN_TABLET) {
        DeleteInformationScreen(popUp = { appState.popUp() })
    }
}

fun NavGraphBuilder.phoneGraph(appState: TrackMateAppState) {
    composable(SEARCH_SCREEN_PHONE.route) {
        SearchScreenPhone(onItemClick = { studentId ->
            appState.navigateWithArgument(
                route = ACTION_DETAIL_SCREEN_PHONE, argument = studentId
            )
        })

    }
    composable(HOME_SCREEN_PHONE.route) {
        HomeScreenPhone()
    }

    composable(
        route = "$ACTION_DETAIL_SCREEN_PHONE/{studentId}",
        arguments = listOf(navArgument("studentId") {
            type = NavType.StringType
        })
    ) {
        DetailScreenPhone(
            popUp = { appState.popUp() }, studentId = it.arguments?.getString("studentId") ?: ""
        )
    }
}