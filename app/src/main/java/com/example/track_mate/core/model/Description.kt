package com.example.track_mate.core.model

import androidx.compose.runtime.Immutable
import java.util.UUID

@Immutable
data class Description(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String = ""
): DeleteListItem
