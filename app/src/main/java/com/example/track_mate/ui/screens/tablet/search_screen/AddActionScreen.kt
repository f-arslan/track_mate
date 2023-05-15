package com.example.track_mate.ui.screens.tablet.search_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppDropDownMenu
import com.example.track_mate.common.composables.SurfaceWrapper
import com.example.track_mate.core.model.Student
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.AddActionViewModel
import com.example.track_mate.ui.screens.view_models.Sections
import com.example.track_mate.use_cases.getCurrentTime
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MAX_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING
import com.example.track_mate.R.drawable as AppIcon
import com.example.track_mate.R.string as AppText


@Composable
fun AddActionScreen(
    popUp: () -> Unit,
    student: Student,
    viewModel: AddActionViewModel = hiltViewModel(),
) {
    val personals by viewModel.personals.collectAsState()
    val descriptions by viewModel.descriptions.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.updateAction(
            Sections.STUDENT,
            newStudent = student
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(MAX_PADDING))
        TimeSection()
        Spacer(modifier = Modifier.height(HIGH_PADDING))
        personals.let {
            if (it is RequestState.Success) {
                AppDropDownMenu(data = it.data.map { data -> data.name },
                    label = AppText.personals,
                    onSelectedItem = { name ->
                        viewModel.updateAction(
                            Sections.PERSONAL,
                            name
                        )
                    })
            }
        }
        Spacer(modifier = Modifier.height(HIGH_PADDING))
        descriptions.let {
            if (it is RequestState.Success) {
                AppDropDownMenu(data = it.data.map { data -> data.name },
                    label = AppText.descriptions,
                    onSelectedItem = { name ->
                        viewModel.updateAction(
                            Sections.DESCRIPTION,
                            name
                        )
                    })
            }
        }
        Spacer(modifier = Modifier.height(HIGH_PADDING))
        AppDropDownMenu(data = viewModel.givenTimes.map { data -> data.name },
            label = AppText.given_times,
            onSelectedItem = { name ->
                viewModel.updateAction(
                    Sections.GIVEN_TIME,
                    name
                )
            })

        Spacer(modifier = Modifier.height(HIGH_PADDING))
        ButtonSection(onConfirmButtonClick = {
            viewModel.onConfirmClick()
            popUp()
        },
            onCancelButtonClick = {
                popUp()
            })
    }
}


private val buttonHeight = 50.dp
private val buttonWidth = 90.dp

@Composable
fun ButtonSection(
    onConfirmButtonClick: () -> Unit = {},
    onCancelButtonClick: () -> Unit = {}
) {
    Row(modifier = Modifier.padding(top = MEDIUM_PADDING)) {
        Button(
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight),
            onClick = onConfirmButtonClick
        ) {
            Icon(
                imageVector = Icons.Default.Done,
                contentDescription = stringResource(AppText.done)
            )
        }
        Divider(
            Modifier
                .width(HIGH_PADDING)
                .height(NO_PADDING)
        )
        OutlinedButton(
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight),
            onClick = onCancelButtonClick
        ) {
            Icon(
                painter = painterResource(AppIcon.outline_cancel_24),
                contentDescription = stringResource(id = AppText.cancel),
            )
        }
    }
}

@Composable
fun TimeSection() {
    SurfaceWrapper(
        modifier = Modifier
            .height(90.dp)
            .width(140.dp),
        tonalElevation = SMALL_MEDIUM_PADDING
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(AppText.exit),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = getCurrentTime(),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}