package com.example.track_mate.core.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
