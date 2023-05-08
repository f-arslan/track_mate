package com.example.track_mate.model

import com.google.firebase.perf.ktx.trace
import com.google.firebase.perf.metrics.Trace

inline fun <T> trace(name: String, block: Trace.() -> T): T = Trace.create(name).trace(block)