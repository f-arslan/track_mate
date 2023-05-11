package com.example.track_mate.ui.screens.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.snackbar.SnackbarManager
import com.example.track_mate.core.model.Description
import com.example.track_mate.core.model.Personal
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.FirebaseNodes
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.core.model.DeleteListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.track_mate.R.string as AppText
enum class ShowState {
    PERSONAL, DESCRIPTION
}

private const val MAX_SIZE = 12

@HiltViewModel
class DeleteInformationViewModel @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {
    private val _personals = MutableStateFlow<RequestState<List<Personal>>>(RequestState.Idle)
    val personals: StateFlow<RequestState<List<Personal>>> = _personals

    private val _descriptions = MutableStateFlow<RequestState<List<Description>>>(RequestState.Idle)
    val descriptions: StateFlow<RequestState<List<Description>>> = _descriptions

    var showState by mutableStateOf(ShowState.PERSONAL)
        private set

    var deleteItems by mutableStateOf<List<DeleteListItem>>(emptyList())
        private set

    init {
        loadPersonals()
        loadDescriptions()
    }

    private fun loadPersonals() {
        viewModelScope.launch {
            storageService.getAllData(FirebaseNodes.PersonalNode) {
                _personals.value = RequestState.Success(it)
            }
        }

    }

    private fun loadDescriptions() {
        viewModelScope.launch {
            storageService.getAllData(FirebaseNodes.DescriptionNode) {
                _descriptions.value = RequestState.Success(it)
            }
        }
    }

    fun updateShowState(state: ShowState) {
        showState = state
    }

    fun addDeleteItem(item: DeleteListItem) {
        if (deleteItems.contains(item)) {
            SnackbarManager.showMessage(AppText.already_added)
            return
        }
        if (deleteItems.size >= MAX_SIZE) {
            SnackbarManager.showMessage(AppText.maximum_size_message)
            return
        }
        deleteItems = deleteItems + item
    }

    fun deleteItem(item: DeleteListItem) {
        if (deleteItems.contains(item)) {
            deleteItems = deleteItems - item
        }
    }

    fun deleteFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteItems.forEach {
                when (it) {
                    is Personal -> storageService.deleteProperty(it.id, FirebaseNodes.PersonalNode)
                    is Description -> storageService.deleteProperty(it.id, FirebaseNodes.DescriptionNode)
                }
            }
        }.invokeOnCompletion {
            deleteItems = emptyList()
        }
    }
}