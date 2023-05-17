package com.example.track_mate.ui.screens.view_models

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.ext.isValidEmail
import com.example.track_mate.common.ext.isValidPassword
import com.example.track_mate.common.ext.passwordMatches
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.util.Constants.EMAIL_ERROR
import com.example.track_mate.util.Constants.PASSWORD_ERROR
import com.example.track_mate.util.Constants.PASSWORD_MATCH_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.track_mate.R.string as AppText

data class SignUpUiState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val rePassword: String = ""
)

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(private val storageService: StorageService) :
    AppViewModel() {
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email

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

    fun onSignUpClick(snackbarHostState: SnackbarHostState) {
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
                    duration = SnackbarDuration.Short,
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

        }
    }
}