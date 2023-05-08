package com.example.track_mate.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
