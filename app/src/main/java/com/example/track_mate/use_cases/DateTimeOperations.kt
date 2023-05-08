package com.example.track_mate.use_cases

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun getCurrentDate(): String {
    val current = LocalDateTime.now()
    return current.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}

fun getCurrentTime(): String {
    val current = LocalDateTime.now()
    return current.format(DateTimeFormatter.ofPattern("HH:mm"))
}


fun calculateTotalTimeOutside(
    startDate: String,
    startTime: String,
    endDate: String,
    endTime: String
): String {
    val startDateTime = LocalDateTime.parse(
        "$startDate $startTime",
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    )
    val endDateTime = LocalDateTime.parse(
        "$endDate $endTime",
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    )
    val totalTime = Duration.between(startDateTime, endDateTime)
    val hours = totalTime.toHours()
    val minutes = totalTime.toMinutes() % 60
    return "${hours}h ${minutes}m"
}