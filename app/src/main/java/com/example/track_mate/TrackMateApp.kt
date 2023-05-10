package com.example.track_mate

import android.content.res.Resources
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.track_mate.common.snackbar.SnackbarManager
import com.example.track_mate.ui.screens.tablet.TabletScreen
import com.example.track_mate.ui.theme.TrackMateTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun TrackMateApp(widthSizeClass: WindowWidthSizeClass, heighthSizeClass: WindowHeightSizeClass) {
    TrackMateTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val isTabletScreen = widthSizeClass == WindowWidthSizeClass.Expanded
            if (isTabletScreen) TabletScreen()
        }
    }
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(navController, snackbarManager, resources, coroutineScope) {
        TrackMateAppState(navController, snackbarManager, resources, coroutineScope)
    }