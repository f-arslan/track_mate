package com.example.track_mate.model.service

import javax.annotation.concurrent.Immutable

@Immutable
data class Student(
    val id: String = "",
    val name: String = ""
)
