package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.track_mate.R
import com.example.track_mate.core.model.ActionStatus
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING
import com.example.track_mate.R.drawable as AppIcon

@Composable
fun FloatingActionButtonApp(
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(
        text = {
            Text(
                text = stringResource(R.string.add),
                fontWeight = FontWeight.SemiBold
            )
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(MEDIUM_PADDING),
        onClick = onClick
    )
}

@Composable
fun ActionButtons(
    status: ActionStatus,
    onApproveClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
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
        OutlinedButton(
            onClick = onDeleteClick,
            shape = RoundedCornerShape(MEDIUM_PADDING)
        ) {
            Icon(
                painterResource(AppIcon.outline_cancel_24),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun AppExtendedButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    ExtendedFloatingActionButton(onClick = onClick,
        modifier = modifier,
        text = { Text(stringResource(text)) },
        icon = {
            Icon(
                painterResource(icon),
                contentDescription = null
            )
        })
}


@Composable
fun DeleteUploadButton(
    onClick: () -> Unit = {}
) {
    Button(
        shape = RoundedCornerShape(SMALL_PADDING),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(AppIcon.baseline_delete_outline_24),
            contentDescription = null
        )
    }
}

private val buttonWidth = 250.dp
private val buttonHeight = 50.dp

@Composable
fun DeleteSectionButton(
    @StringRes text: Int,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight),
        enabled = enabled,
        shape = RoundedCornerShape(SMALL_PADDING),
        onClick = onClick
    ) {
        Text(text = stringResource(text))
    }
}

@Composable
fun DeleteSectionLabel(@StringRes text: Int) {
    Surface(
        modifier = Modifier
            .width(buttonWidth)
            .height(buttonHeight),
        tonalElevation = VERY_HIGH_PADDING,
        shadowElevation = SMALL_PADDING,
        shape = RoundedCornerShape(SMALL_PADDING)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(text), fontWeight = FontWeight.SemiBold)
        }
    }
}