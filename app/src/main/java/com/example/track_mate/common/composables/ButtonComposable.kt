package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.track_mate.R
import com.example.track_mate.model.ActionStatus
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.R.drawable as AppIcon

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

@Composable
fun ActionButtons(status: ActionStatus, onApproveClick: () -> Unit, onDeleteClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)) {
        if (status == ActionStatus.ON_GOING) {
            Button(
                onClick = onApproveClick,
                shape = RoundedCornerShape(
                    MEDIUM_PADDING
                )
            ) {
                Icon(
                    painterResource(AppIcon.baseline_done_24),
                    contentDescription = null,
                )
            }
        }
        OutlinedButton(onClick = onDeleteClick, shape = RoundedCornerShape(MEDIUM_PADDING)) {
            Icon(
                painterResource(AppIcon.outline_cancel_24),
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}

@Composable
fun AppExtendedButton(
    modifier: Modifier = Modifier, @StringRes text: Int, @DrawableRes icon: Int, onClick: () -> Unit
) {
    ExtendedFloatingActionButton(onClick = onClick,
        modifier = modifier,
        text = { Text(stringResource(text)) },
        icon = { Icon(painterResource(icon), contentDescription = null) })
}
