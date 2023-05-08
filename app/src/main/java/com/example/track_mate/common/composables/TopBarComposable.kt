package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING

@Composable
fun AppTopBar(
    text: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = SMALL_MEDIUM_PADDING, bottom = SMALL_MEDIUM_PADDING
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text, style = MaterialTheme.typography.displayMedium)
        Divider(thickness = 1.dp)
    }
}