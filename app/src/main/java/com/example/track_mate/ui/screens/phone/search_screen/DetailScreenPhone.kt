package com.example.track_mate.ui.screens.phone.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.DetailGridPhone
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.TrackMateTopAppBar
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.DetailScreenViewModel
import com.example.track_mate.util.TrackMateIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPhone(
    popUp: () -> Unit,
    studentId: String,
    viewModel: DetailScreenViewModel = hiltViewModel(),
) {
    viewModel.initStudent(studentId)
    val actions by viewModel.allActions.collectAsState()
    Column {
        actions.apply {
            if (this is RequestState.Success) {
                TrackMateTopAppBar(
                    title = data[0].student.name,
                    navigationIcon = TrackMateIcons.BackArrow,
                    navigationIconContentDescription = null,
                    actionIcon = null,
                    actionIconContentDescription = null,
                    onNavigationClick = popUp
                )
                DetailGridPhone(items = data)
            } else {
                EmptyScreen(isPhone = true)
            }
        }
    }
}

