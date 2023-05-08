package com.example.track_mate.model

import androidx.compose.runtime.Immutable
import java.util.UUID

interface ListItem {
    val id: String
    val name: String
}

@Immutable
data class Description(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String = ""
): ListItem
