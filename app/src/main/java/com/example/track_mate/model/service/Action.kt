package com.example.track_mate.model.service

import java.util.UUID
import javax.annotation.concurrent.Immutable

enum class ActionStatus {
    ON_GOING, COMPLETE
}

@Immutable
data class Action(
    val id: String = UUID.randomUUID().toString(),
    val student: Student = Student(),
    val personal: String = "",
    val startTime: String = "",
    val startDate: String = "",
    val endTime: String = "",
    val endDate: String = "",
    val givenTime: String = "",
    val totalTimeOutside: String = "Still Outside",
    val description: String = "",
    val status: ActionStatus = ActionStatus.ON_GOING,
    val notifyState: Int = 0
) {
    fun getStartDateTime(): String {
        return "$startDate $startTime"
    }

    fun getEndDateTime(): String {
        return if (endDate.isEmpty()) {
            "Still Outside"
        } else {
            "$endDate $endTime"
        }
    }

    fun getTotalTime(): String {
        return if (totalTimeOutside.isEmpty()) {
            "Still Outside"
        } else {
            totalTimeOutside
        }
    }
}