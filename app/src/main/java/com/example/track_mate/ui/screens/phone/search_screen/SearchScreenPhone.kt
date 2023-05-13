package com.example.track_mate.ui.screens.phone.search_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppSearchBar
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.SurfaceWrapper
import com.example.track_mate.core.model.Student
import com.example.track_mate.ui.screens.view_models.SearchScreenViewModel
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING

@Composable
fun SearchScreenPhone(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val firstTenStudents by viewModel.firstTenStudents.collectAsState()
    val uiState by viewModel.uiState
    Column(modifier = Modifier.padding(HIGH_PADDING)) {
        AppSearchBar(
            value = uiState.textState,
            trailingIconState = uiState.trailingIconState,
            onValueChange = viewModel::onTextChange,
            onCancel = viewModel::onCancel
        )

        if (firstTenStudents.isNotEmpty()) {
            Spacer(modifier = Modifier.padding(MEDIUM_PADDING))
            QueryResultSection(
                data = firstTenStudents,
                onItemClick = onItemClick
            )
        } else {
            EmptyScreen(isPhone = true)
        }

    }
}

@Composable
fun QueryResultSection(
    data: List<Student>,
    onItemClick: (String) -> Unit
) {
    if (data.isEmpty()) {
        return
    }
    val studentLabel = if (data.size < 2) "student" else "students"
    Column {
        Text(
            text = "${data.size} $studentLabel found",
            Modifier
                .alpha(0.6f)
                .padding(start = SMALL_MEDIUM_PADDING),
            style = MaterialTheme.typography.bodyMedium
        )
        SurfaceWrapper(tonalElevation = SMALL_MEDIUM_PADDING) {
            LazyColumn(
                contentPadding = PaddingValues(MEDIUM_PADDING),
            ) {
                items(items = data,
                    key = { it.id }) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(it.id)
                            },
                        text = it.name,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
