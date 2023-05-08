package com.example.track_mate.model.service

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Personal(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String = ""
):ListItem
