package com.example.track_mate.ui.screens.tablet.screens.search_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun DetailScreen(
    student: Student,
    detailScreenViewModelApp: DetailScreenViewModelApp = hiltViewModel()
) {
    detailScreenViewModelApp.initStudent(student)
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