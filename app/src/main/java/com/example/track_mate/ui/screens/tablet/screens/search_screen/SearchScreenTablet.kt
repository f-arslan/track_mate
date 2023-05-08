package com.example.track_mate.ui.screens.tablet.screens.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.example.track_mate.ACTION_DETAIL_SCREEN_TABLET
import com.example.track_mate.ADD_ACTION_SCREEN_TABLET
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.AppTopBar
import com.example.track_mate.common.composables.FloatingActionButtonApp
import com.example.track_mate.model.service.Student
import com.example.track_mate.rememberAppState
import com.example.track_mate.ui.screens.view_models.SearchScreenViewModel
import com.example.track_mate.ui.screens.view_models.UiState
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING

@Composable
fun SearchScreenTablet(viewModel: SearchScreenViewModel = hiltViewModel()) {
    val firstTenStudents by viewModel.firstTenStudents.collectAsState()
    val uiState by viewModel.uiState
    Row(
        Modifier
        .fillMaxSize()
        .padding(VERY_HIGH_PADDING)) {

        SearchSection(
            modifier = Modifier
                .weight(0.3f)
                .fillMaxSize(),
            uiState = uiState,
            firstTenStudents = firstTenStudents,
            onSearchBarTextChange = viewModel::onTextChange,
            onStudentClick = viewModel::onSelectedStudents,
            onCancel = viewModel::onCancel
        )

        Divider(
            Modifier
            .width(HIGH_PADDING)
            .height(NO_PADDING)
        )

        Card(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxSize(),
            shape = RoundedCornerShape(
                MEDIUM_PADDING
            )
        ) {
            if (uiState.selectedStudent.name == "") {
                EmptyScreen()
            } else {
                DetailSectionTablet(student = uiState.selectedStudent)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailSectionTablet(
    student: Student,
) {
    val appState = rememberAppState()
    Scaffold(
        topBar = {
            AppTopBar(
                text = student.name
            )
        },
        floatingActionButton = {
            FloatingActionButtonApp(
                onClick = {
                    appState.navigate(ADD_ACTION_SCREEN_TABLET)
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = appState.navController,
            startDestination = ACTION_DETAIL_SCREEN_TABLET
        ) {
            stmsSearchScreenGraph(appState, student)
        }
    }
}

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    uiState: UiState,
    firstTenStudents: List<Student>,
    onSearchBarTextChange: (String) -> Unit,
    onStudentClick: (Student) -> Unit,
    onCancel: () -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(MEDIUM_PADDING),
    ) {
        Column(modifier = Modifier.padding(MEDIUM_HIGH_PADDING)) {
            StmsSearchBar(
                value = uiState.textState,
                trailingIconState = uiState.trailingIconState,
                onValueChange = onSearchBarTextChange,
                onCancel = onCancel
            )
            if (firstTenStudents.isNotEmpty()) {
                SearchList(
                    firstTenStudents = firstTenStudents,
                    onStudentClick = onStudentClick
                )
            }
        }
    }
}


