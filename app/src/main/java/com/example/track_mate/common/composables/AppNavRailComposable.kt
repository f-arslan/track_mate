package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.track_mate.HOME_SCREEN_TABLET
import com.example.track_mate.Route
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.SETTING_SCREEN_TABLET
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NAV_RAIL_WIDTH
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.R.string as AppText

@Composable
fun AppNavRail(clearAndNavigate: (String) -> Unit) {
    var selectedRouteIndex by remember { mutableStateOf(1) }

    fun navigateToScreen(screenRoute: String, index: Int) {
        selectedRouteIndex = index
        clearAndNavigate(screenRoute)
    }

    fun setIconType(selected: Boolean, route: Route): Int {
        return if (selected) route.iconFilled else route.iconOutline
    }

    NavigationRail(modifier = Modifier.width(NAV_RAIL_WIDTH)) {
        Column(
            modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center,
        ) {
            AppNavRailItem(
                selected = selectedRouteIndex == 0,
                icon = setIconType(selectedRouteIndex == 0, HOME_SCREEN_TABLET),
                label = AppText.home,
                onClick = { navigateToScreen(HOME_SCREEN_TABLET.route, 0) },
            )
            Spacer(with(Modifier) { height(MEDIUM_PADDING).width(NO_PADDING) })
            AppNavRailItem(
                selected = selectedRouteIndex == 1,
                icon = SEARCH_SCREEN_TABLET.iconOutline,
                label = AppText.search,
                onClick = { navigateToScreen(SEARCH_SCREEN_TABLET.route, 1) },
            )
            Spacer(with(Modifier) { height(MEDIUM_PADDING).width(NO_PADDING) })
            AppNavRailItem(
                selected = selectedRouteIndex == 2,
                icon = setIconType(selectedRouteIndex == 2, SETTING_SCREEN_TABLET),
                label = AppText.settings,
                onClick = { navigateToScreen(SETTING_SCREEN_TABLET.route, 2) },
            )
        }
    }
}

@Composable
fun AppNavRailItem(
    selected: Boolean,
    @DrawableRes icon: Int,
    @StringRes label: Int,
    onClick: () -> Unit,
) {
    NavigationRailItem(selected = selected, onClick = onClick, icon = {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
        )
    }, label = {
        Column(
            modifier = Modifier.width(NAV_RAIL_WIDTH),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(stringResource(label))
        }
    })
}