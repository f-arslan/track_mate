package com.example.track_mate.model.service.module

import com.example.track_mate.model.Action
import com.example.track_mate.model.Description
import com.example.track_mate.model.Personal
import com.example.track_mate.model.Student

sealed class FirebaseNodes<T>(val section: String, val classType: Class<T>) {
    object StudentNode :
        FirebaseNodes<Student>(section = "Students", classType = Student::class.java)

    object ActionNode :
        FirebaseNodes<Action>(section = "Actions", classType = Action::class.java)

    object PersonalNode :
        FirebaseNodes<Personal>(section = "Personals", classType = Personal::class.java)

    object DescriptionNode :
        FirebaseNodes<Description>(section = "Descriptions", classType = Description::class.java)
}