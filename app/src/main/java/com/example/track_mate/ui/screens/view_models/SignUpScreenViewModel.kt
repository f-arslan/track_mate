package com.example.track_mate.ui.screens.view_models

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.ext.isValidEmail
import com.example.track_mate.common.ext.isValidPassword
import com.example.track_mate.common.ext.passwordMatches
import com.example.track_mate.core.model.service.AuthRepository
import com.example.track_mate.core.model.service.SendEmailVerificationResponse
import com.example.track_mate.core.model.service.SignUpResponse
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.Response
import com.example.track_mate.util.Constants.CHECK_YOUR_VERIFY
import com.example.track_mate.util.Constants.EMAIL_ERROR
import com.example.track_mate.util.Constants.PASSWORD_ERROR
import com.example.track_mate.util.Constants.PASSWORD_MATCH_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignUpUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val rePassword: String = ""
)

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val storageService: StorageService, private val authRepository: AuthRepository
) : AppViewModel() {
    var uiState = mutableStateOf(SignUpUiState("Fatih", "f@gmail.com", "Mkal858858", "Mkal858858"))
        private set

    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set
    var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
        Response.Success(
            false
        )
    )
        private set

    private val email
        get() = uiState.value.email.trim()

    private val password
        get() = uiState.value.password

    fun onNameChange(newValue: String) {
        uiState.value = uiState.value.copy(fullName = newValue)
    }

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRePasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(rePassword = newValue)
    }

    fun onSignUpClick(
        snackbarHostState: SnackbarHostState,
        openAndPopUp: () -> Unit,
    ) {
        viewModelScope.launch {
            if (!email.isValidEmail()) {
                snackbarHostState.showSnackbar(
                    message = EMAIL_ERROR,
                    duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
                return@launch
            }
            if (!password.isValidPassword()) {
                snackbarHostState.showSnackbar(
                    message = PASSWORD_ERROR,
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
                return@launch
            }
            if (!password.passwordMatches(uiState.value.rePassword)) {
                snackbarHostState.showSnackbar(
                    message = PASSWORD_MATCH_ERROR,
                    duration = SnackbarDuration.Short,
                    withDismissAction = true
                )
                return@launch
            }
            signUpWithEmailAndPassword(email, password)
            if (signUpResponse is Response.Success) {
                sendEmailVerification(snackbarHostState)
                openAndPopUp()
            }
        }
    }

    private fun signUpWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = authRepository.firebaseSignUpWithEmailAndPassword(email, password)
    }

    private fun sendEmailVerification(snackbarHostState: SnackbarHostState) = viewModelScope.launch {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = authRepository.sendEmailVerification()
        if (sendEmailVerificationResponse is Response.Success) {
            snackbarHostState.showSnackbar(
                message = CHECK_YOUR_VERIFY,
                duration = SnackbarDuration.Long,
                withDismissAction = true
            )
        }
    }
}