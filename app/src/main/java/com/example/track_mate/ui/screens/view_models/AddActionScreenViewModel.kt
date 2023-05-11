package com.example.track_mate.ui.screens.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.track_mate.core.model.Action
import com.example.track_mate.core.model.DataSource
import com.example.track_mate.core.model.Description
import com.example.track_mate.core.model.Personal
import com.example.track_mate.core.model.Student
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.FirebaseNodes
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.use_cases.getCurrentDate
import com.example.track_mate.use_cases.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class Sections {
    STUDENT, PERSONAL, DESCRIPTION, GIVEN_TIME, START_TIME, START_DATE
}

@HiltViewModel
class AddActionViewModel @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {

    private val _personals = MutableStateFlow<RequestState<List<Personal>>>(RequestState.Idle)
    val personals: StateFlow<RequestState<List<Personal>>>
        get() = _personals

    private val _descriptions = MutableStateFlow<RequestState<List<Description>>>(RequestState.Idle)
    val descriptions: StateFlow<RequestState<List<Description>>>
        get() = _descriptions

    val givenTimes = DataSource.givenTimes

    private var currentAction by mutableStateOf(Action())

    init {
        getPersonals()
        getDescriptions()
    }

    private fun getPersonals() {
        currentAction = currentAction.copy(givenTime = givenTimes[0].name)
        viewModelScope.launch(Dispatchers.IO) {
            storageService.getAllData(FirebaseNodes.PersonalNode) {
                _personals.value = RequestState.Success(it)
                currentAction = currentAction.copy(personal = it[0].name)
            }
        }
    }

    private fun getDescriptions() {
        viewModelScope.launch(Dispatchers.IO) {
            storageService.getAllData(FirebaseNodes.DescriptionNode) {
                _descriptions.value = RequestState.Success(it)
                currentAction = currentAction.copy(description = it[0].name)
            }
        }
    }


    fun updateAction(
        propertyToModify: Sections,
        newValue: String = "",
        newStudent: Student = Student()
    ) {
        currentAction = when (propertyToModify) {
            Sections.STUDENT -> currentAction.copy(student = newStudent)
            Sections.PERSONAL -> currentAction.copy(personal = newValue)
            Sections.DESCRIPTION -> currentAction.copy(description = newValue)
            Sections.GIVEN_TIME -> currentAction.copy(givenTime = newValue)
            Sections.START_TIME -> currentAction.copy(startTime = getCurrentTime())
            Sections.START_DATE -> currentAction.copy(startDate = getCurrentDate())
        }
    }

    fun onConfirmClick() {
        updateAction(Sections.START_TIME)
        updateAction(Sections.START_DATE)
        addActionToDatabase()
    }

    private fun addActionToDatabase() {
        storageService.addProperty(
            dataId = currentAction.id,
            data = currentAction,
            node = FirebaseNodes.ActionNode
        )
    }
}