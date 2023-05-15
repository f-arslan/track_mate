package com.example.track_mate.ui.screens.tablet.sign_up_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppExtendedButton
import com.example.track_mate.common.composables.EmailField
import com.example.track_mate.common.composables.PasswordField
import com.example.track_mate.ui.screens.view_models.SignUpScreenViewModel
import com.example.track_mate.ui.screens.view_models.SignUpUiState
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MAX_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING
import com.example.track_mate.util.Constants.VERY_MAX_PADDING
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText

@Composable
fun SignUpScreenProvider(viewModel: SignUpScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    SignUpScreen(
        uiState,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onConfirmPasswordChange = viewModel::onRePasswordChange,
        onSignUpClick = viewModel::onSignUpClick
    )
}

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxSize()) {
        InformationSection(modifier = Modifier.weight(0.5f))
        FormSectionSignUp(
            modifier = Modifier.weight(0.5f),
            uiState,
            onNameChange,
            onEmailChange,
            onPasswordChange,
            onConfirmPasswordChange,
            onSignUpClick
        )
    }
}

@Composable
fun FormSectionSignUp(
    modifier: Modifier,
    uiState: SignUpUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit
) {
    Surface(modifier = modifier.fillMaxHeight()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(VERY_HIGH_PADDING))
            Text(
                text = stringResource(AppText.create_account),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(MAX_PADDING))
            EmailField(uiState.fullName, AppText.full_name, onNameChange)
            Spacer(modifier = Modifier.height(MEDIUM_HIGH_PADDING))
            EmailField(uiState.email, AppText.email, onEmailChange)
            Spacer(modifier = Modifier.height(MEDIUM_HIGH_PADDING))
            PasswordField(uiState.password, AppText.password, onPasswordChange)
            Spacer(modifier = Modifier.height(MEDIUM_HIGH_PADDING))
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                PasswordField(uiState.rePassword, AppText.repeat_password, onConfirmPasswordChange)
                Spacer(modifier = Modifier.height(HIGH_PADDING))
                AppExtendedButton(
                    modifier = Modifier.fillMaxWidth(),
                    AppText.sign_up,
                    TrackMateIcons.ForwardArrow,
                    onSignUpClick
                )
            }
            Spacer(Modifier.height(VERY_MAX_PADDING))
            Row {
                Text(stringResource(AppText.have_an_account))
                Spacer(modifier = Modifier.width(SMALL_PADDING))
                Text(
                    stringResource(AppText.login),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}


@Composable
fun InformationSection(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxHeight(), color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Column(modifier = Modifier.padding(VERY_HIGH_PADDING)) {
            Text(
                text = stringResource(AppText.app_name),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(VERY_HIGH_PADDING))
            Text(
                text = stringResource(AppText.display_title),
                style = MaterialTheme.typography.displayLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(SMALL_PADDING))
            Text(
                text = stringResource(AppText.display_text),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


@Preview(showBackground = true, device = "id:pixel_tablet")
@Composable
fun SignUpPreview() {
    SignUpScreen(uiState = SignUpUiState(), {}, {}, {}, {}, {})
}