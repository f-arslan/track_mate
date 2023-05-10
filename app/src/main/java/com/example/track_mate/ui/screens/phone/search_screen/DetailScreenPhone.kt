package com.example.track_mate.ui.screens.phone.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.DetailGridPhone
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.HeaderPhone
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.DetailScreenViewModel

@Composable
fun DetailScreenPhone(
    studentId: String,
    viewModel: DetailScreenViewModel = hiltViewModel(),
) {
    viewModel.initStudent(studentId)
    val actions by viewModel.allActions.collectAsState()
    Column {
        actions.apply {
            if (this is RequestState.Success) {
                HeaderPhone(data[0].student.name)
                DetailGridPhone(items = data)
            } else {
                EmptyScreen()
            }
        }
    }
}

