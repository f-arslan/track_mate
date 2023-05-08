package com.example.track_mate.common.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.track_mate.R
import com.example.track_mate.util.Constants.MEDIUM_PADDING

@Composable
fun FloatingActionButtonApp(
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(text = {
        Text(text = stringResource(R.string.add), fontWeight = FontWeight.SemiBold)
    },
        icon = {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
        },
        shape = RoundedCornerShape(MEDIUM_PADDING),
        onClick = onClick
    )
}