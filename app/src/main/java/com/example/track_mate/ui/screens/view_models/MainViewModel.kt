package com.example.track_mate.ui.screens.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.track_mate.core.model.service.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    init {
        getAuthState()
    }

    fun getAuthState() = authRepository.getAuthState(viewModelScope)

    val isEmailVerified get() = authRepository.currentUser?.isEmailVerified ?: false
}