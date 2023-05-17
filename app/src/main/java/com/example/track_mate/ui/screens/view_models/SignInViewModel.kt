package com.example.track_mate.ui.screens.view_models

import androidx.compose.runtime.mutableStateOf
import com.example.track_mate.common.ext.isValidEmail
import com.example.track_mate.common.ext.isValidPassword
import com.example.track_mate.common.ext.passwordMatches
import com.example.track_mate.core.model.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class SignInUiState(
    val email: String = "", val password: String = ""
)

@HiltViewModel
class SignInViewModel @Inject constructor(private val storageService: StorageService) :
    AppViewModel() {

    val uiState = mutableStateOf(SignInUiState())

    private val email
        get() = uiState.value.email

    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onSignInClick() {

    }
}