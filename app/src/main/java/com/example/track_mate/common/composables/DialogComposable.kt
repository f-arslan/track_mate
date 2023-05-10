package com.example.track_mate.common.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.R.string as AppText
import com.example.track_mate.R.drawable as AppIcon

@Composable
fun AppDialog(
    updateDialogState: (Boolean) -> Unit,
    onDoneClick: () -> Unit,
) {
    Dialog(onDismissRequest = { updateDialogState(false) }) {
        Card(
            shape = RoundedCornerShape(
                MEDIUM_PADDING
            )
        ) {
            Column(
                modifier = Modifier.padding(HIGH_PADDING),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(stringResource(AppText.are_you_sure))
                Divider(
                    Modifier
                        .height(HIGH_PADDING)
                        .width(NO_PADDING)
                )
                Row {
                    Button(
                        shape = RoundedCornerShape(MEDIUM_PADDING),
                        onClick = {
                            updateDialogState(false)
                            onDoneClick()
                        }) {
                        Icon(painter = painterResource(AppIcon.baseline_done_24), contentDescription = null)
                    }
                    Divider(
                        Modifier
                            .width(MEDIUM_PADDING)
                            .height(NO_PADDING)
                    )
                    OutlinedButton(
                        shape = RoundedCornerShape(MEDIUM_PADDING),
                        onClick = {
                            updateDialogState(false)
                        }) {
                        Icon(painter = painterResource(AppIcon.outline_cancel_24), contentDescription = null)
                    }
                }
            }
        }
    }
}