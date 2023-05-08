package com.example.track_mate

import androidx.annotation.DrawableRes
import com.example.track_mate.R.drawable as AppIcon
data class Route(
    val route: String,
    @DrawableRes val iconOutline: Int,
    @DrawableRes val iconFilled: Int = iconOutline,
)

// common screen
const val SPLASH_SCREEN = "SplashScreen"

// Tablet screens
const val LOGIN_SCREEN_TABLET = "LoginScreenTablet"
const val SIGN_UP_SCREEN_TABLET = "SignUpScreenTablet"
val HOME_SCREEN_TABLET = Route(route = "HomeScreenTablet", iconOutline = AppIcon.outline_home_24, iconFilled = AppIcon.baseline_home_24)
val SEARCH_SCREEN_TABLET = Route(route = "SearchScreenTablet", iconOutline = AppIcon.baseline_search_24)
const val EMPTY_DETAIL_SCREEN_TABLET = "EmptyDetailScreenTablet"
const val ACTION_DETAIL_SCREEN_TABLET = "ActionDetailScreenTablet"
const val ADD_ACTION_SCREEN_TABLET = "AddActionScreenTablet"
val SETTING_SCREEN_TABLET = Route(route = "SettingScreenTablet", iconOutline = AppIcon.outline_settings_24, iconFilled = AppIcon.baseline_settings_24)
const val ADD_INFORMATION_SCREEN_TABLET = "AddInformationScreenTablet"
const val DELETE_INFORMATION_SCREEN_TABLET = "DeleteInformationScreenTablet"
const val PRESENTATION_SCREEN_TABLET = "PresentationScreenTablet"

// Phone screens
const val LOGIN_SCREEN_PHONE = "LoginScreenPhone"
const val SIGN_UP_SCREEN_PHONE = "SignUpScreenPhone"
val HOME_SCREEN_PHONE = Route(route = "HomeScreenPhone", iconOutline = AppIcon.baseline_home_24)
val SEARCH_SCREEN_PHONE = Route(route = "SearchScreenPhone", iconOutline = AppIcon.baseline_search_24)
const val ACTION_DETAIL_SCREEN_PHONE = "ActionDetailScreenPhone"
const val SETTING_SCREEN_PHONE = "SettingScreenPhone"