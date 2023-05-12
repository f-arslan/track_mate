package com.example.track_mate.ui.screens.tablet.setting_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.example.track_mate.ADD_INFORMATION_SCREEN_TABLET
import com.example.track_mate.DELETE_INFORMATION_SCREEN_TABLET
import com.example.track_mate.PRESENTATION_SCREEN_TABLET
import com.example.track_mate.common.composables.AppExtendedButton
import com.example.track_mate.common.composables.CardWrapper
import com.example.track_mate.common.composables.SurfaceWrapper
import com.example.track_mate.common.composables.TrackMateTopAppBar
import com.example.track_mate.rememberAppState
import com.example.track_mate.ui.screens.graph.settingScreenGraph
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING
import com.example.track_mate.util.Constants.VERY_SMALL_PADDING
import com.example.track_mate.R.string as AppText
import com.example.track_mate.R.drawable as AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreenTablet() {
    val appState = rememberAppState()
    SurfaceWrapper(modifier = Modifier.padding(HIGH_PADDING), tonalElevation = VERY_SMALL_PADDING) {
        Scaffold {
            NavHost(
                navController = appState.navController,
                startDestination = PRESENTATION_SCREEN_TABLET,
                modifier = Modifier.padding(it)
            ) {
                settingScreenGraph(appState = appState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingPresentationScreen(navigate: (String) -> Unit) {
    val buttonWidth = (LocalConfiguration.current.screenWidthDp / 4).dp
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TrackMateTopAppBar(
            title = stringResource(AppText.settings),
            navigationIcon = null,
            navigationIconContentDescription = null,
            actionIcon = null,
            actionIconContentDescription = null
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppExtendedButton(
                modifier = Modifier.width(buttonWidth),
                text = AppText.add_information_screen,
                icon = AppIcon.outline_add_circle_outline_24,
                onClick = {
                    navigate(ADD_INFORMATION_SCREEN_TABLET)
                }
            )
            Divider(
                Modifier
                    .height(VERY_HIGH_PADDING)
                    .width(NO_PADDING)
            )
            AppExtendedButton(
                modifier = Modifier.width(buttonWidth),
                text = AppText.delete_information_screen,
                icon = AppIcon.baseline_delete_outline_24,
                onClick = {
                    navigate(DELETE_INFORMATION_SCREEN_TABLET)
                }
            )
        }
    }
}