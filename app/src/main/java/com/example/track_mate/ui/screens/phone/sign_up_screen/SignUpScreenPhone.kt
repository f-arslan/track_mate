package com.example.track_mate.ui.screens.phone.sign_up_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppExtendedButton
import com.example.track_mate.common.composables.EmailField
import com.example.track_mate.common.composables.InformationHeader
import com.example.track_mate.common.composables.PasswordField
import com.example.track_mate.ui.screens.view_models.SignUpScreenViewModel
import com.example.track_mate.ui.screens.view_models.SignUpUiState
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MAX_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreenPhoneProvider(
    viewModel: SignUpScreenViewModel = hiltViewModel(),
    openAndPopUp: () -> Unit,
    onHaveAnAccountClick: () -> Unit
) {
    val uiState by viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboard = LocalSoftwareKeyboardController.current
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        SignUpScreenPhone(
            Modifier.padding(it),
            uiState = uiState,
            onFullNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onRePasswordChange = viewModel::onRePasswordChange,
            onSignUpClick = {
                keyboard?.hide()
                viewModel.onSignUpClick(snackbarHostState, openAndPopUp)
            },
            onHaveAccountClick = onHaveAnAccountClick
        )
    }
}


@Composable
fun SignUpScreenPhone(
    modifier: Modifier,
    uiState: SignUpUiState,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRePasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onHaveAccountClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        InformationHeader(Modifier.fillMaxHeight(0.2f))
        FormSectionSignUpPhone(
            Modifier.fillMaxHeight(1f),
            uiState,
            onFullNameChange,
            onEmailChange,
            onPasswordChange,
            onRePasswordChange,
            onSignUpClick,
            onHaveAccountClick
        )
    }
}

@Composable
fun FormSectionSignUpPhone(
    modifier: Modifier,
    uiState: SignUpUiState,
    onFullNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRePasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onHaveAccountClick: () -> Unit,
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.padding(top = MEDIUM_PADDING))
            Text(
                text = stringResource(AppText.create_account),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.padding(top = HIGH_PADDING))
            EmailField(uiState.fullName, AppText.full_name, onFullNameChange)
            Spacer(Modifier.padding(top = MEDIUM_HIGH_PADDING))
            EmailField(uiState.email, AppText.email, onEmailChange)
            Spacer(Modifier.padding(top = MEDIUM_HIGH_PADDING))
            PasswordField(uiState.password, AppText.password, onPasswordChange)
            Spacer(Modifier.padding(top = MEDIUM_HIGH_PADDING))
            Column(Modifier.width(IntrinsicSize.Max)) {
                PasswordField(uiState.rePassword, AppText.repeat_password, onRePasswordChange)
                Spacer(Modifier.padding(top = HIGH_PADDING))
                AppExtendedButton(
                    modifier = Modifier.fillMaxWidth(),
                    AppText.sign_up,
                    TrackMateIcons.ForwardArrow,
                    onSignUpClick
                )
            }
            Spacer(Modifier.padding(top = MAX_PADDING))
            TextButton(onClick = onHaveAccountClick) {
                Text(
                    text = stringResource(AppText.have_an_account_login),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUp() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SignUpScreenPhone(Modifier, uiState = SignUpUiState(), {}, {}, {}, {}, {}, {})
    }
}

