package com.example.track_mate.ui.screens.tablet.sign_in_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.track_mate.common.composables.InformationSection
import com.example.track_mate.common.composables.PasswordField
import com.example.track_mate.ui.screens.view_models.SignInUiState
import com.example.track_mate.ui.screens.view_models.SignInViewModel
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MAX_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.VERY_MAX_PADDING
import com.example.track_mate.util.TrackMateIcons
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.track_mate.R.string as AppText

@Composable
fun SignInScreenProvider(viewModel: SignInViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    rememberSystemUiController().setStatusBarColor(MaterialTheme.colorScheme.surfaceTint)
    SignInScreen(
        uiState,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onSignInClick = viewModel::onSignInClick
    )
}

@Composable
fun SignInScreen(
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Row {
            InformationSection(modifier = Modifier.weight(0.5f))
            FormSectionSignIn(
                modifier = Modifier.weight(0.5f),
                uiState,
                onEmailChange,
                onPasswordChange,
                onSignInClick
            )
        }
    }
}

@Composable
fun FormSectionSignIn(
    modifier: Modifier,
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit
) {
    Surface(modifier = modifier.fillMaxHeight()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(Modifier.padding(top = MAX_PADDING))
            Text(
                text = stringResource(AppText.hello_again),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.padding(top = MEDIUM_PADDING))
            Text(
                text = stringResource(AppText.welcome_back_you_have_been_missed),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.padding(top = VERY_MAX_PADDING))
            Column(
                modifier = Modifier.width(IntrinsicSize.Max), horizontalAlignment = Alignment.End
            ) {
                EmailField(
                    value = uiState.email, placeholder = AppText.email, onNewValue = onEmailChange
                )
                Spacer(Modifier.padding(top = MEDIUM_HIGH_PADDING))
                PasswordField(
                    value = uiState.password,
                    placeholder = AppText.password,
                    onNewValue = onPasswordChange
                )
                Spacer(Modifier.padding(top = MEDIUM_PADDING))
                Text(
                    text = stringResource(AppText.recovery_password),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.padding(top = HIGH_PADDING))
                AppExtendedButton(
                    Modifier.fillMaxWidth(),
                    AppText.sign_in,
                    TrackMateIcons.ForwardArrow,
                    onSignInClick
                )
            }
            Spacer(Modifier.padding(top = VERY_MAX_PADDING))
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(AppText.not_a_member_register),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }

        }
    }
}

@Preview(showBackground = true, device = "spec:parent=pixel_tablet")
@Composable
fun SignInPreview() {
    Column(
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInScreen(SignInUiState(), {}, {}, {})
    }
}
