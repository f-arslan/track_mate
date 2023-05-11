package com.example.track_mate.core.model

import javax.annotation.concurrent.Immutable

@Immutable
data class Student(
    val id: String = "",
    val name: String = ""
)
