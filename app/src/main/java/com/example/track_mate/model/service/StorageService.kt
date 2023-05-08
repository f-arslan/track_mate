package com.example.track_mate.model.service

import com.example.track_mate.model.Action
import com.example.track_mate.model.service.module.FirebaseNodes

interface StorageService {
    fun <T> getAllData(node: FirebaseNodes<T>, onDataChangeListener: (List<T>) -> Unit)
    fun getAllActionsByStudentId(studentId: String, onDataChangeListener: (List<Action>) -> Unit)
    fun <T> getAllDataByDay(node: FirebaseNodes<T>, onDataChangeListener: (List<T>) -> Unit)
    fun <T> deleteProperty(id: String, node : FirebaseNodes<T>)
    fun <T> addProperty(dataId: String, data: T, node: FirebaseNodes<T>)
}