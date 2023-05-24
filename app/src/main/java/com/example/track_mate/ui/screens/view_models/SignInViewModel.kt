package com.example.track_mate.ui.screens.view_models

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.ext.isValidEmail
import com.example.track_mate.core.model.service.AuthRepository
import com.example.track_mate.core.model.service.SignInResponse
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SignInUiState(
    val email: String = "", val password: String = ""
)

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val storageService: StorageService, private val authRepository: AuthRepository
) : AppViewModel() {

    val uiState = mutableStateOf(SignInUiState("fatiharslanedu@gmail.com", "Mkal858858"))
    var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))
        private set

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

    fun onSignInClick(snackbarHostState: SnackbarHostState, openAndPopUp: () -> Unit) {
        fun showSnackbar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
            viewModelScope.launch {
                snackbarHostState.showSnackbar(
                    message = message, duration = duration, withDismissAction = true
                )
            }
        }
        if (!email.isValidEmail()) {
            return
        }
        if (password.isBlank()) {
            return
        }
        viewModelScope.launch {
            async { signInWithEmailAndPassword(email, password) }.await()
            if (signInResponse is Response.Failure) {
                (signInResponse as Response.Failure).e.message?.let { showSnackbar(it) }
                return@launch
            }
            openAndPopUp()
        }
    }

    private suspend fun signInWithEmailAndPassword(email: String, password: String) {
        signInResponse = Response.Loading
        signInResponse = authRepository.firebaseSignInWithEmailAndPassword(email, password)
    }
}