package com.example.track_mate.ui.screens.tablet.search_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.ActionGrid
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.core.model.Action
import com.example.track_mate.core.model.Student
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.DetailScreenViewModel


@Composable
fun DetailScreen(
    student: Student,
    detailScreenViewModelApp: DetailScreenViewModel = hiltViewModel()
) {
    detailScreenViewModelApp.initStudent(student.id)
    val allActions by detailScreenViewModelApp.allActions.collectAsState()
    when (allActions) {
        is RequestState.Success -> {
            ActionGrid(
                data = (allActions as RequestState.Success<List<Action>>).data,
                finishActionClick = detailScreenViewModelApp::finishAction,
                deleteActionClick = detailScreenViewModelApp::deleteAction
            )
        }
        else -> {
            EmptyScreen()
        }
    }
}