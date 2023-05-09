package com.example.track_mate.ui.screens.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.snackbar.SnackbarManager
import com.example.track_mate.model.Description
import com.example.track_mate.model.Personal
import com.example.track_mate.model.service.StorageService
import com.example.track_mate.model.service.module.FirebaseNodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.track_mate.R.string as AppText

enum class ButtonState {
    SAVE, POST
}

data class SettingUiState(
    val personalName: String = "",
    val descriptionName: String = "",
    val buttonState: ButtonState = ButtonState.SAVE,
    val isTextFieldEnabled: Boolean = true
)

@HiltViewModel
class AddInformationViewModel @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {
    val uiState = mutableStateOf(SettingUiState())

    fun onPersonalNameChange(personalName: String) {
        uiState.value = uiState.value.copy(personalName = personalName)
    }

    fun onDescriptionChange(description: String) {
        uiState.value = uiState.value.copy(descriptionName = description)
    }

    fun onSaveClick() {
        uiState.value.run {
            if (personalName.isEmpty() && descriptionName.isNotEmpty()) {
                SnackbarManager.showMessage(AppText.make_sure_to_fill_in_at_least_one_field)
                return
            }
        }
        uiState.value = uiState.value.copy(buttonState = ButtonState.POST, isTextFieldEnabled = false)
        uiState.value = uiState.value.copy(isTextFieldEnabled = false)
    }

    fun onCancelClick() {
        reset()
    }

    fun onUploadClick() {
        val nPersonal = Personal(name = uiState.value.personalName)
        val nDescription = Description(name = uiState.value.descriptionName)
        viewModelScope.launch(Dispatchers.IO) {
            storageService.addProperty(nPersonal.id, nPersonal, FirebaseNodes.PersonalNode)
            storageService.addProperty(nDescription.id, nDescription, FirebaseNodes.DescriptionNode)
        }
        reset()
    }

    private fun reset() {
        uiState.value = SettingUiState()
    }

}