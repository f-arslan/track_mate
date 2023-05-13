package com.example.track_mate.ui.screens.phone.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.DetailGridPhone
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.TrackMateTopAppBar
import com.example.track_mate.core.model.Action
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.HomeScreenViewModelApp
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPhone(
    viewModel: HomeScreenViewModelApp = hiltViewModel()
) {
    val allStudentsDaily by viewModel.allActionsByToday.collectAsState()
    val context = LocalContext.current
    Column {
        TrackMateTopAppBar(title = stringResource(AppText.home),
            navigationIcon = null,
            navigationIconContentDescription = null,
            actionIcon = TrackMateIcons.SaveAlt,
            actionIconContentDescription = null,
            onActionClick = { viewModel.writeToFileAsCsv(context) })
        when (allStudentsDaily) {
            is RequestState.Success -> DetailGridPhone(
                items = (allStudentsDaily as RequestState.Success<List<Action>>).data
            )
            else -> {
                EmptyScreen(isPhone = true)
            }
        }
    }
}