package com.example.track_mate.ui.screens.view_models

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.track_mate.common.snackbar.SnackbarManager
import com.example.track_mate.core.model.Action
import com.example.track_mate.core.model.ActionStatus
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.FirebaseNodes
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.use_cases.calculateTotalTimeOutside
import com.example.track_mate.use_cases.getCurrentDate
import com.example.track_mate.use_cases.getCurrentTime
import com.example.track_mate.use_cases.writeToCsvFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModelApp @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {

    private val _allActionsByToday = MutableStateFlow<RequestState<List<Action>>>(RequestState.Idle)
    val allActionsByToday: StateFlow<RequestState<List<Action>>>
        get() = _allActionsByToday
    private val allActions = MutableStateFlow<RequestState<List<Action>>>(RequestState.Idle)

    init {
        getStudentsByToday()
    }

    private fun getStudentsByToday() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                storageService.getAllDataByDay(FirebaseNodes.ActionNode) {
                    when {
                        it.isEmpty() -> _allActionsByToday.value = RequestState.Loading
                        else -> _allActionsByToday.value = RequestState.Success(it)
                    }
                }
            }
        } catch (e: Exception) {
            _allActionsByToday.value = RequestState.Error(e)
        }
    }

    private fun getAllActions() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                storageService.getAllData(FirebaseNodes.ActionNode) {
                    allActions.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            allActions.value = RequestState.Error(e)
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

    fun writeToFileAsCsv(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            // Check if allStudents has a value
            if (allActions.value is RequestState.Idle) {
                // If it doesn't, call getStudents and wait for it to complete
                getAllActions()
                allActions.collect { state ->
                    if (state !is RequestState.Idle) {
                        // Once we have a value, proceed to write to the CSV file
                        if (state is RequestState.Success) {
                            val currentDate = getCurrentDate()
                            writeToCsvFile(
                                context = context,
                                currentDate = currentDate,
                                actions = state.data
                            )
                            SnackbarManager.showMessage("Your file save as $currentDate.csv")
                        }
                    }
                }
            } else {
                // If allStudents already has a value, proceed to write to the CSV file
                val allStudentsResult = allActions.value
                if (allStudentsResult is RequestState.Success) {
                    val currentDate = getCurrentDate()
                    writeToCsvFile(
                        context = context,
                        currentDate = currentDate,
                        actions = allStudentsResult.data
                    )
                    SnackbarManager.showMessage("Your file save as $currentDate.csv")

                }
            }
        }
    }

}