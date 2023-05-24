package com.example.track_mate.ui.screens.view_models

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.track_mate.core.model.service.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _snackbarMessage = mutableStateOf("")
    val snackbarMessage get() = _snackbarMessage.value

    init {
        getAuthState()
        print()
    }

    fun updateSnackbarMessage(message: String) {
        _snackbarMessage.value = message
    }

    fun onSnackbarDismiss() {
        _snackbarMessage.value = ""
    }

    private fun getAuthState() = authRepository.getAuthState(viewModelScope)

    val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
    fun print() {
        Log.d("MainViewModel", "${authRepository.currentUser?.email} ${authRepository.currentUser?.isEmailVerified}")
    }
}
