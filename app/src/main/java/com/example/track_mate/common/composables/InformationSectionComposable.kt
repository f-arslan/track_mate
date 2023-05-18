package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.track_mate.R
import com.example.track_mate.util.Constants

@Composable
fun InformationHeader(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceTint,
    ) {
        Column(modifier = Modifier.padding(Constants.HIGH_PADDING)) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.padding(top = Constants.VERY_HIGH_PADDING))
            Text(
                text = stringResource(R.string.display_text),
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun InformationSection(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxHeight(), color = MaterialTheme.colorScheme.surfaceTint
    ) {
        Column(modifier = Modifier.padding(Constants.VERY_HIGH_PADDING)) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(Constants.VERY_HIGH_PADDING))
            Text(
                text = stringResource(R.string.display_title),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(Constants.SMALL_PADDING))
            Text(
                text = stringResource(R.string.display_text),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
