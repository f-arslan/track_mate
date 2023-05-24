package com.example.track_mate.ui.screens.view_models

import android.util.Log
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
import com.example.track_mate.util.Constants.EMAIL_ERROR
import com.example.track_mate.util.Constants.PASSWORD_ERROR
import com.example.track_mate.util.Constants.PASSWORD_MATCH_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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
    var uiState = mutableStateOf(
        SignUpUiState(
            "Fatih", "fatiharslanedu@gmail.com", "Mkal858858", "Mkal858858"
        )
    )
        private set

    private var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
    private var sendEmailVerificationResponse by mutableStateOf<SendEmailVerificationResponse>(
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
        snackbarHostState: SnackbarHostState, openAndPopUp: () -> Unit
    ) {
        fun showSnackbar(message: String, duration: SnackbarDuration) {
            viewModelScope.launch {
                snackbarHostState.showSnackbar(
                    message = message, duration = duration, withDismissAction = true
                )
            }
        }
        if (!email.isValidEmail()) {
            showSnackbar(EMAIL_ERROR, SnackbarDuration.Short)
            return
        }
        if (!password.isValidPassword()) {
            showSnackbar(PASSWORD_ERROR, SnackbarDuration.Short)
            return
        }
        if (!password.passwordMatches(uiState.value.rePassword)) {
            showSnackbar(PASSWORD_MATCH_ERROR, SnackbarDuration.Short)
            return
        }
        viewModelScope.launch {
            async { signUpWithEmailAndPassword(email, password) }.await()
            if (signUpResponse is Response.Failure) {
                showSnackbar(
                    (signUpResponse as Response.Failure).e.message ?: "",
                    SnackbarDuration.Short
                )
                return@launch
            }
            async { sendEmailVerification() }.await()
            openAndPopUp()
        }
    }

    private suspend fun signUpWithEmailAndPassword(email: String, password: String) {
        signUpResponse = Response.Loading
        signUpResponse = authRepository.firebaseSignUpWithEmailAndPassword(email, password)
    }

    private suspend fun sendEmailVerification() {
        sendEmailVerificationResponse = Response.Loading
        sendEmailVerificationResponse = authRepository.sendEmailVerification()
    }
}