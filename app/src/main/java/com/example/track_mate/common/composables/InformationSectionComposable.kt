package com.example.track_mate.common.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.track_mate.R
import com.example.track_mate.util.Constants

@Composable
fun InformationHeader(modifier: Modifier) {
    val backgroundColor by animateColorAsState(targetValue = MaterialTheme.colorScheme.surfaceTint,
        label = "BackgroundColor"
    )
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(Constants.HIGH_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.padding(top = Constants.MEDIUM_PADDING))
            Text(
                text = stringResource(R.string.display_text_phone),
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

@Composable
fun InformationSection(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxHeight(), color = MaterialTheme.colorScheme.surfaceTint
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Constants.VERY_HIGH_PADDING),
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(Constants.HIGH_PADDING))
            Text(
                text = stringResource(R.string.display_title),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(Constants.SMALL_PADDING))
            Text(
                text = stringResource(R.string.display_text),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInfo() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        InformationHeader(modifier = Modifier)
    }
}