package com.example.track_mate.ui.screens.tablet.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.ActionGrid
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.SurfaceWrapper
import com.example.track_mate.common.composables.TrackMateTopAppBar
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.graph.TopLevelDestination
import com.example.track_mate.ui.screens.view_models.HomeScreenViewModelApp
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.VERY_SMALL_PADDING
import com.example.track_mate.util.TrackMateIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTablet(
    homeScreenViewModel: HomeScreenViewModelApp = hiltViewModel()
) {
    val context = LocalContext.current
    val allActionsByToday by homeScreenViewModel.allActionsByToday.collectAsState()

    SurfaceWrapper(
        modifier = Modifier.padding(HIGH_PADDING),
        tonalElevation = VERY_SMALL_PADDING
    ) {
        Scaffold(topBar = {
            TrackMateTopAppBar(
                title = stringResource(TopLevelDestination.HOME.titleTextId),
                navigationIcon = null,
                navigationIconContentDescription = null,
                actionIcon = TrackMateIcons.SaveAlt,
                actionIconContentDescription = null
            ) { homeScreenViewModel.writeToFileAsCsv(context) }
        }) { values ->
            Column(modifier = Modifier.padding(values)) {
                when (val result = allActionsByToday) {
                    is RequestState.Success -> {
                        ActionGrid(
                            data = result.data,
                            finishActionClick = homeScreenViewModel::finishAction,
                            deleteActionClick = homeScreenViewModel::deleteAction
                        )
                    }

                    else -> {
                        EmptyScreen()
                    }
                }
            }
        }
    }
}