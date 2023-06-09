package com.example.track_mate.core.model.service.impl

import com.example.track_mate.core.model.Action
import com.example.track_mate.core.model.ActionStatus
import com.example.track_mate.core.model.service.StorageService
import com.example.track_mate.core.model.service.module.FirebaseNodes
import com.example.track_mate.use_cases.getCurrentDate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : StorageService {
    private val studentsRef = firebaseDatabase.reference.child("Students")
    private val actionsRef = firebaseDatabase.reference.child("Actions")
    private val personalsRef = firebaseDatabase.reference.child("Personals")
    private val descriptionsRef = firebaseDatabase.reference.child("Descriptions")

    override fun <T> getAllData(node: FirebaseNodes<T>, onDataChangeListener: (List<T>) -> Unit) {
        firebaseDatabase.reference.child(node.section)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children.mapNotNull { it.getValue(node.classType) }
                    onDataChangeListener(data)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    override fun getAllActionsByStudentId(
        studentId: String,
        onDataChangeListener: (List<Action>) -> Unit
    ) {
        actionsRef.orderByChild("student/id").equalTo(studentId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val data = snapshot.children.mapNotNull { it.getValue(Action::class.java) }
                        .sortedBy { it.status }
                    onDataChangeListener(data)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

    override fun <T> getAllDataByDay(
        node: FirebaseNodes<T>,
        onDataChangeListener: (List<T>) -> Unit
    ) {
        getAllData(node = node) {
            val data = it.filter { data ->
                when (node) {
                    FirebaseNodes.ActionNode -> (data as Action).startDate == getCurrentDate()
                    else -> false
                }
            }.sortedBy { data ->
                when (node) {
                    FirebaseNodes.ActionNode -> (data as Action).status == ActionStatus.COMPLETE
                    else -> false
                }
            }
            onDataChangeListener(data)
        }
    }

    override fun <T> deleteProperty(id: String, node: FirebaseNodes<T>) {
        when (node) {
            FirebaseNodes.ActionNode -> actionsRef
            FirebaseNodes.DescriptionNode -> descriptionsRef
            FirebaseNodes.PersonalNode -> personalsRef
            FirebaseNodes.StudentNode -> studentsRef
        }.child(id).removeValue()
    }

    override fun <T> addProperty(dataId: String, data: T, node: FirebaseNodes<T>) {
        when (node) {
            FirebaseNodes.ActionNode -> actionsRef
            FirebaseNodes.DescriptionNode -> descriptionsRef
            FirebaseNodes.PersonalNode -> personalsRef
            FirebaseNodes.StudentNode -> studentsRef
        }.child(dataId).setValue(data)
    }
}