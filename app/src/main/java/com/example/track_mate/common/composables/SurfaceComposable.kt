package com.example.track_mate.common.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.util.Constants.VERY_SMALL_PADDING

@Composable
fun ActionSurfaceWrapper(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(MEDIUM_PADDING),
        shadowElevation = SMALL_PADDING,
        tonalElevation = VERY_SMALL_PADDING
    ) {
        content()
    }
}


@Composable
fun SurfaceWrapper(
    modifier: Modifier = Modifier,
    tonalElevation: Dp = NO_PADDING,
    shadowElevation: Dp = SMALL_PADDING,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(
            MEDIUM_PADDING
        ),
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation
    ) {
        content()
    }
}