package com.example.track_mate.ui.screens.phone.search_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.track_mate.ACTION_DETAIL_SCREEN_PHONE
import com.example.track_mate.common.composables.AppSearchBar
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.core.model.Student
import com.example.track_mate.ui.screens.view_models.SearchScreenViewModel
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING

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
            QueryResultSection(
                data = firstTenStudents,
                onItemClick = onItemClick
            )
        } else {
            EmptyScreen()
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
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = NO_PADDING,
            topEnd = NO_PADDING,
            bottomStart = SMALL_PADDING,
            bottomEnd = SMALL_PADDING
        ),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(MEDIUM_PADDING),
        ) {
            items(items = data, key = { it.id }) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(it.id)
                        },
                    text = it.name,
                )
            }
        }
    }
}
