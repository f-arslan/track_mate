package com.example.track_mate.ui.screens.phone.sign_in_screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppExtendedButton
import com.example.track_mate.common.composables.EmailField
import com.example.track_mate.common.composables.InformationHeader
import com.example.track_mate.common.composables.PasswordField
import com.example.track_mate.ui.screens.view_models.SignInUiState
import com.example.track_mate.ui.screens.view_models.SignInViewModel
import com.example.track_mate.util.Constants.MAX_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreenPhoneProvider(
    viewModel: SignInViewModel = hiltViewModel(),
    onRegisterClick: () -> Unit,
    onSignInClick: () -> Unit
) {
    val uiState by viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(snackbarHost = { SnackbarHost(snackbarHostState) }) {
        SignInScreenPhone(
            Modifier.padding(it),
            uiState,
            onEmailChange = viewModel::onEmailChange,
            onPasswordChange = viewModel::onPasswordChange,
            onSignInClick = { viewModel.onSignInClick(snackbarHostState) { onSignInClick() } },
            onForgotPasswordClick = {},
            onRegisterClick = onRegisterClick
        )
    }
}

@Composable
fun SignInScreenPhone(
    modifier: Modifier,
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        InformationHeader(Modifier.fillMaxHeight(0.2f))
        FormSectionSignInPhone(
            Modifier.fillMaxHeight(),
            uiState,
            onEmailChange,
            onPasswordChange,
            onSignInClick,
            onForgotPasswordClick,
            onRegisterClick
        )
    }
}

@Composable
fun FormSectionSignInPhone(
    modifier: Modifier,
    uiState: SignInUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignInClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.padding(top = MAX_PADDING))
            EmailField(uiState.email, AppText.email, onEmailChange)
            Spacer(Modifier.padding(top = MEDIUM_HIGH_PADDING))
            Column(Modifier.width(IntrinsicSize.Max), horizontalAlignment = Alignment.End) {
                PasswordField(uiState.password, AppText.password, onPasswordChange)
                TextButton(onClick = onForgotPasswordClick) {
                    Text(
                        text = stringResource(AppText.forgot_password),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(Modifier.padding(top = MEDIUM_PADDING))
                AppExtendedButton(
                    modifier = Modifier.fillMaxWidth(),
                    AppText.sign_in,
                    TrackMateIcons.ForwardArrow,
                    onSignInClick
                )
            }
            Spacer(Modifier.padding(top = MAX_PADDING))
            TextButton(onClick = onRegisterClick) {
                Text(
                    text = stringResource(AppText.not_a_member_register),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SignInScreenPhone(Modifier, uiState = SignInUiState(), {}, {}, {}, {}, {})
    }
}
