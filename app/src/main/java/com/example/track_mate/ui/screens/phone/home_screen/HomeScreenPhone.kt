package com.example.track_mate.ui.screens.phone.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.DetailGridPhone
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.HeaderPhone
import com.example.track_mate.model.Action
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.HomeScreenViewModelApp
import com.example.track_mate.util.Constants.HOME

@Composable
fun HomeScreenPhone(
    homeScreenViewModelApp: HomeScreenViewModelApp = hiltViewModel()
) {
    val allStudentsDaily by homeScreenViewModelApp.allActionsByToday.collectAsState()
    val context = LocalContext.current
    Column {
        HeaderPhone(
            name = HOME,
            state = HOME,
            saveIconClick = {
                homeScreenViewModelApp.writeToFileAsCsv(context)
            })
        when (allStudentsDaily) {
            is RequestState.Success -> DetailGridPhone(
                items = (allStudentsDaily as RequestState.Success<List<Action>>).data
            )

            else -> {
                EmptyScreen()
            }
        }
    }
}