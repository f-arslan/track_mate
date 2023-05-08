package com.example.track_mate.use_cases

import android.content.Context
import com.example.track_mate.model.service.Action
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter


fun writeToCsvFile(context: Context, currentDate: String, actions: List<Action>) {
    // Save under the android obb
    val file = File(context.getExternalFilesDir(null), "$currentDate.csv")
    val csvWriter = CSVWriter(FileWriter(file))
    csvWriter.writeNext(
        arrayOf(
            "Öğrenci Adı",
            "Personal Adı",
            "Başlangıç Tarihi",
            "Bitiş Tarihi",
            "Verilen Zaman",
            "Toplam Geçen Zaman",
            "Description"
        )
    )
    actions.forEach {
        val endTime =
            if (it.endTime.isNotEmpty()) "${it.endDate} ${it.endTime}" else "Still Outside"
        csvWriter.writeNext(
            arrayOf(
                it.student.name,
                it.personal,
                "${it.startDate} ${it.startTime}",
                endTime,
                it.givenTime,
                it.totalTimeOutside,
                it.description
            )
        )
    }
    csvWriter.close()
}