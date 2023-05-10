package com.example.track_mate.ui.screens.tablet.setting_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppTextField
import com.example.track_mate.common.composables.AppTopBar
import com.example.track_mate.ui.screens.view_models.AddInformationViewModel
import com.example.track_mate.ui.screens.view_models.ButtonState
import com.example.track_mate.util.Constants.DESCRIPTION
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.PERSONAL
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.R.string as AppText
import com.example.track_mate.R.drawable as AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInformationScreen(
    addInformationViewModel: AddInformationViewModel = hiltViewModel()
) {
    val uiState by addInformationViewModel.uiState
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            AppTopBar(text = stringResource(AppText.add_information_screen))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTextField(
                    label = PERSONAL,
                    value = uiState.personalName,
                    enabled = uiState.isTextFieldEnabled,
                    onValueChange = addInformationViewModel::onPersonalNameChange
                )
                Divider(
                    Modifier
                        .height(MEDIUM_PADDING)
                        .width(NO_PADDING)
                )
                AppTextField(
                    label = DESCRIPTION,
                    value = uiState.descriptionName,
                    enabled = uiState.isTextFieldEnabled,
                    onValueChange = addInformationViewModel::onDescriptionChange
                )
                Divider(
                    Modifier
                        .height(MEDIUM_PADDING)
                        .width(NO_PADDING)
                )
                AddInformationButtonSection(
                    onSaveClick = addInformationViewModel::onSaveClick,
                    onUploadClick = addInformationViewModel::onUploadClick,
                    onCancelClick = addInformationViewModel::onCancelClick,
                    buttonState = uiState.buttonState
                )
            }
        }
    }
}


@Composable
fun AddInformationButtonSection(
    onSaveClick: () -> Unit,
    onUploadClick: () -> Unit,
    onCancelClick: () -> Unit,
    buttonState: ButtonState = ButtonState.SAVE
) {
    Row(horizontalArrangement = Arrangement.spacedBy(HIGH_PADDING)) {
        if (buttonState == ButtonState.SAVE) {
            Button(
                shape = RoundedCornerShape(MEDIUM_PADDING),
                onClick = onSaveClick
            ) {
                Icon(
                    painter = painterResource(AppIcon.baseline_save_alt_24),
                    contentDescription = null
                )
            }
        }
        if (buttonState == ButtonState.POST) {
            Button(
                shape = RoundedCornerShape(MEDIUM_PADDING),
                onClick = onUploadClick
            ) {
                Icon(
                    painterResource(id = AppIcon.outline_cloud_upload_24),
                    contentDescription = null
                )
            }
        }
        if (buttonState == ButtonState.POST) {
            OutlinedButton(
                shape = RoundedCornerShape(MEDIUM_PADDING),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = SMALL_PADDING),
                onClick = onCancelClick
            ) {
                Icon(
                    painter = painterResource(AppIcon.outline_cancel_24),
                    contentDescription = null,
                )
            }
        }
    }
}

