package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING

@Composable
fun CardWrapper(content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(VERY_HIGH_PADDING),
        shape = RoundedCornerShape(MEDIUM_PADDING)
    ) {
        content()
    }
}

@Composable
fun ActionSurfaceWrapper(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    // TODO: look for elevation options
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(MEDIUM_PADDING)
    ) {
        content()
    }
}