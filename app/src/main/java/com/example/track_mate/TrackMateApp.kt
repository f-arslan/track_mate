package com.example.track_mate

import android.Manifest
import android.content.res.Resources
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.track_mate.common.composables.PermissionDialog
import com.example.track_mate.common.composables.RationaleDialog
import com.example.track_mate.common.snackbar.SnackbarManager
import com.example.track_mate.ui.screens.graph.topLevelPhoneGraph
import com.example.track_mate.ui.screens.graph.topLevelTabletGraph
import com.example.track_mate.ui.screens.view_models.MainViewModel
import com.example.track_mate.ui.theme.TrackMateTheme
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackMateApp(
    widthSizeClass: WindowWidthSizeClass, viewModel: MainViewModel
) {
    TrackMateTheme {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            RequestNotificationPermissionDialog()
        }
        val snackbarHostState = remember { SnackbarHostState() }
        val message = viewModel.snackbarMessage
        Scaffold(snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState, modifier = Modifier.padding(MEDIUM_PADDING)
            )
        }) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background
            ) {
                // Show app level snackbar
                LaunchedEffect(message){
                    if (message.isNotEmpty()) {
                        snackbarHostState.showSnackbar(
                            message, duration = SnackbarDuration.Long, withDismissAction = true
                        )
                        viewModel.onSnackbarDismiss()
                    }
                }

                val tabletAppState = rememberAppState()
                val phoneAppState = rememberAppState()
                val isTabletScreen =
                    widthSizeClass == WindowWidthSizeClass.Expanded || widthSizeClass == WindowWidthSizeClass.Medium
                if (isTabletScreen) {
                    NavHost(
                        navController = tabletAppState.navController,
                        startDestination = SPLASH_SCREEN
                    ) {
                        topLevelTabletGraph(tabletAppState, viewModel)
                    }
                } else {
                    NavHost(
                        navController = phoneAppState.navController,
                        startDestination = SPLASH_SCREEN
                    ) {
                        topLevelPhoneGraph(phoneAppState, viewModel)
                    }
                }
            }

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
) = remember(
    navController, snackbarManager, resources, coroutineScope
) {
    TrackMateAppState(
        navController, snackbarManager, resources, coroutineScope
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestNotificationPermissionDialog() {
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    if (!permissionState.status.isGranted) {
        if (permissionState.status.shouldShowRationale) RationaleDialog()
        else PermissionDialog { permissionState.launchPermissionRequest() }
    }
}