package com.example.track_mate.ui.screens.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.track_mate.model.service.StorageService
import com.example.track_mate.model.Student
import com.example.track_mate.model.service.module.FirebaseNodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val textState: String = "",
    val selectedStudent: Student = Student(),
    val trailingIconState: Boolean = false
)


@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val storageService: StorageService
) : AppViewModel() {

    private val _students = MutableStateFlow<List<Student>>(emptyList())

    private val _firstTenStudents = MutableStateFlow<List<Student>>(emptyList())
    val firstTenStudents: StateFlow<List<Student>> = _firstTenStudents
    val uiState = mutableStateOf(SettingUiState())

    init {
        getStudents()
    }

    private fun getStudents() {
        viewModelScope.launch(Dispatchers.IO) {
            storageService.getAllData(FirebaseNodes.StudentNode) {
                _students.value = it
            }
        }
    }

    fun getStudentsWithQuery(query: String) {
        if (_students.value.isEmpty()) {
            return
        }
        val filteredStudents = _students.value.filter {
            it.name.contains(query, true)
        }.take(10)
        _firstTenStudents.value = filteredStudents
    }

    fun onTextChange(text: String) {
        uiState.value = uiState.value.copy(textState = text)
        uiState.value = uiState.value.copy(trailingIconState = true)
        if (text == "") {
            onCancel()
            return
        }
        getStudentsWithQuery(text)
    }

    fun onSelectedStudents(selectedStudent: Student) {
        uiState.value = uiState.value.copy(selectedStudent = selectedStudent)
    }

    fun onCancel() {
        uiState.value = SettingUiState()
        _firstTenStudents.value = emptyList()
    }
}