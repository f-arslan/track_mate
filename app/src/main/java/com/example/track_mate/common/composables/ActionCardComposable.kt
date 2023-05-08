package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.track_mate.model.Action
import com.example.track_mate.model.ActionStatus
import com.example.track_mate.model.Student
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.R.drawable as AppIcon
@Composable
fun ActionCard(action: Action, onApproveClick: () -> Unit, onDeleteClick: () -> Unit) {
    var isButtonVisible by remember { mutableStateOf(false) }

    ActionSurfaceWrapper(modifier = Modifier.clickable {
        isButtonVisible = !isButtonVisible
    }) {
        Column {
            ActionStatusRow(action.student.name, action.status)
            Column(modifier = Modifier.padding(MEDIUM_PADDING)) {
                Column(verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)) {
                    InfoRowSection(AppIcon.baseline_supervisor_account_24, action.personal)
                    InfoRowSection(AppIcon.outline_timelapse_24, action.getStartDateTime())
                    InfoRowSection(AppIcon.baseline_access_time_filled_24, action.getEndDateTime())
                    InfoRowSection(AppIcon.outline_timer_24, action.givenTime)
                    InfoRowSection(AppIcon.outline_timelapse_24, action.getTotalTime())
                    InfoRowSection(AppIcon.outline_description_24, action.description)
                    AnimatedVisibility(visible = isButtonVisible) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            ActionButtons(action.status, onApproveClick, onDeleteClick)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ActionStatusRow(
    name: String, status: ActionStatus
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (status == ActionStatus.ON_GOING) Blue else Gray)
            .padding(SMALL_PADDING),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun InfoRowSection(@DrawableRes icon: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(icon), contentDescription = null)
        Text(text = text, fontWeight = FontWeight.SemiBold)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewActionCard() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionCard(action = Action(
            student = Student(name = "Fatih Arslan"),
            personal = "Hello Arslan",
            startDate = "24-07-2023",
            startTime = "20:59",
            endDate = "25-07-2023",
            endTime = "20:17",
            givenTime = "30 minutes",
            totalTimeOutside = "24",
            description = "Hospital"
        ), {}, {})
    }
}