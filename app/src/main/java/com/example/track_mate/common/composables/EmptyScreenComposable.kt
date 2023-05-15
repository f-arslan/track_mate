package com.example.track_mate.common.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.track_mate.util.EmptyScreen


@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    isPhone: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = EmptyScreen,
            contentDescription = null,
            modifier = if (isPhone) Modifier.size(350.dp) else Modifier.size(500.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primaryContainer)
        )
    }
}