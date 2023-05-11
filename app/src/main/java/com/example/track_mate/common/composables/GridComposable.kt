package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.track_mate.core.model.Action
import com.example.track_mate.util.Constants.BOTTOM_NAV_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING

@Composable
fun ActionGrid(
    modifier: Modifier = Modifier,
    data: List<Action>,
    finishActionClick: (Action) -> Unit,
    deleteActionClick: (String) -> Unit,
) {
    var isScrollable by remember { mutableStateOf(true) }
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(MEDIUM_HIGH_PADDING),
        userScrollEnabled = isScrollable,
        columns = GridCells.Adaptive(200.dp),
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        contentPadding = PaddingValues(SMALL_PADDING)
    ) {
        items(items = data, key = { it.id }) { item ->
            ActionCard(action = item, onApproveClick = {
                isScrollable = false
                finishActionClick(item)
                isScrollable = true
            }, onDeleteClick = { deleteActionClick(item.id) })
        }
    }
}

@Composable
fun DetailGridPhone(
    items: List<Action>
) {
    if (items.isEmpty()) {
        EmptyScreen()
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        contentPadding = PaddingValues(
            start = MEDIUM_PADDING,
            end = MEDIUM_PADDING,
            top = MEDIUM_PADDING,
            bottom = BOTTOM_NAV_PADDING
        )
    ) {
        items(items = items, key = { it.id }) {
            ActionCardPhone(action = it)
        }
    }
}