package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.track_mate.HOME_SCREEN_TABLET
import com.example.track_mate.SEARCH_SCREEN_TABLET
import com.example.track_mate.SETTING_SCREEN_TABLET
import com.example.track_mate.util.Constants.ICON_SIZE
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING
import com.example.track_mate.R.drawable as AppIcon
import com.example.track_mate.R.string as AppText

@Composable
fun NavRailHeader() {
    Column(Modifier.padding(MEDIUM_PADDING)) {
        Spacer(with(Modifier) { height(SMALL_MEDIUM_PADDING).width(NO_PADDING) })
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = MEDIUM_PADDING),
            shape = RoundedCornerShape(MEDIUM_PADDING),
        ) {
            Image(painter = painterResource(id = AppIcon.icon_hd_64),
                modifier = Modifier.size(ICON_SIZE),
                contentDescription = null)
        }
    }
}

@Composable
fun AppNavRail(clearAndNavigate: (String) -> Unit) {
    var selectedRouteIndex by remember { mutableStateOf(1) }

    fun navigateToScreen(screenRoute: String, index: Int) {
        selectedRouteIndex = index
        clearAndNavigate(screenRoute)
    }

    NavigationRail {
        Column(modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            AppNavRailItem(
                selected = selectedRouteIndex == 0,
                icon = HOME_SCREEN_TABLET.icon,
                label = AppText.home,
                onClick = { navigateToScreen(HOME_SCREEN_TABLET.route, 0) },
            )
            Spacer(with(Modifier) { height(MEDIUM_PADDING).width(NO_PADDING) })
            AppNavRailItem(
                selected = selectedRouteIndex == 1,
                icon = SEARCH_SCREEN_TABLET.icon,
                label = AppText.search,
                onClick = { navigateToScreen(SEARCH_SCREEN_TABLET.route, 1) },
            )
            Spacer(with(Modifier) { height(MEDIUM_PADDING).width(NO_PADDING) })
            AppNavRailItem(
                selected = selectedRouteIndex == 2,
                icon = SETTING_SCREEN_TABLET.icon,
                label = AppText.setting,
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
    val tint = if (selected) {
        Color.Black
    } else {
        Color.Black.copy(alpha = 0.5f)
    }

    NavigationRailItem(selected = selected,
        onClick = onClick,
        label = { Text(stringResource(label), color = tint) },
        icon = { Icon(painter = painterResource(icon), tint = tint, contentDescription = null) })
}