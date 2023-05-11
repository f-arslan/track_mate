package com.example.track_mate.common.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.track_mate.core.model.Action
import com.example.track_mate.core.model.ActionStatus
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.util.Constants.STILL_OUTSIDE
import com.example.track_mate.R.drawable as AppIcon


@Composable
fun ActionCardPhone(
    action: Action
) {
    Card(
        shape = RoundedCornerShape(SMALL_PADDING),
        modifier = Modifier.padding(SMALL_MEDIUM_PADDING)
    ) {
        Column {
            ActionCardHeader(
                studentName = action.student.name,
                status = action.status
            )
            ActionCardSections(
                personalName = action.personal,
                description = action.description,
                startTime = "${action.startDate} ${action.startTime}",
                endTime = if (action.status == ActionStatus.COMPLETE) "${action.endDate} ${action.endTime}" else STILL_OUTSIDE
            )
        }
    }
}

@Composable
fun ActionCardSections(
    personalName: String,
    description: String,
    startTime: String,
    endTime: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(SMALL_PADDING)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(SMALL_MEDIUM_PADDING)) {
            ActionCardItem(icon = AppIcon.baseline_person_4_24, text = personalName)
            ActionCardItem(icon = AppIcon.outline_description_24, text = description)
            ActionCardItem(icon = AppIcon.baseline_access_time_24, text = startTime)
            ActionCardItem(icon = AppIcon.baseline_access_time_filled_24, text = endTime)
        }
    }
}

@Composable
fun ActionCardItem(@DrawableRes icon: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        Icon(painter = painterResource(icon), contentDescription = null)
        Text(
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ActionCardHeader(
    studentName: String,
    status: ActionStatus
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MEDIUM_PADDING),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = studentName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            painter = painterResource(AppIcon.baseline_fiber_manual_record_24),
            contentDescription = null
        )
    }
}