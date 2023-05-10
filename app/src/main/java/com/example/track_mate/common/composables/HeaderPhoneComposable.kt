package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.R.drawable as AppIcon
import com.example.track_mate.R.string as AppText

@Composable
fun HeaderPhone(
    name: String, state: String = "", saveIconClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier.padding(HIGH_PADDING),
        shape = RoundedCornerShape(SMALL_PADDING),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                modifier = Modifier.padding(
                    bottom = MEDIUM_PADDING,
                    top = MEDIUM_PADDING
                )
            )
            if (state == stringResource(AppText.home)) {
                IconButton(onClick = saveIconClick) {
                    Icon(
                        painter = painterResource(AppIcon.baseline_save_alt_24),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}