package com.example.track_mate.ui.screens.view_models

import androidx.lifecycle.viewModelScope
import com.example.track_mate.model.service.Action
import com.example.track_mate.model.service.ActionStatus
import com.example.track_mate.model.service.StorageService
import com.example.track_mate.model.service.Student
import com.example.track_mate.model.service.module.FirebaseNodes
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.use_cases.calculateTotalTimeOutside
import com.example.track_mate.use_cases.getCurrentDate
import com.example.track_mate.use_cases.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {
    private val _allActions = MutableStateFlow<RequestState<List<Action>>>(RequestState.Idle)
    val allActions: StateFlow<RequestState<List<Action>>> = _allActions

    private val _student = MutableStateFlow(Student())
    val student: StateFlow<Student> = _student

    private fun getAllActions() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                storageService.getAllActionsByStudentId(student.value.id) {
                    _allActions.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _allActions.value = RequestState.Error(e)
        }
    }

    fun finishAction(action: Action) {
        val endDate = getCurrentDate()
        val endTime = getCurrentTime()
        val totalTimeOutside = calculateTotalTimeOutside(
            startDate = action.startDate,
            startTime = action.startTime,
            endDate = endDate,
            endTime = endTime
        )
        storageService.addProperty(
            dataId = action.id,
            data = action.copy(
                status = ActionStatus.COMPLETE,
                endDate = endDate,
                endTime = endTime,
                totalTimeOutside = totalTimeOutside,
                notifyState = 1
            ),
            node = FirebaseNodes.ActionNode
        )
    }

    fun deleteAction(actionId: String) {
        viewModelScope.launch {
            storageService.deleteProperty(actionId, FirebaseNodes.ActionNode)
        }
    }

    fun initStudent(nStudent: Student) {
        _student.value = nStudent
        getAllActions()
    }
}