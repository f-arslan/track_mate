package com.example.track_mate.util

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SaveAlt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

object TrackMateIcons {
    val HomeOutline = Icons.Outlined.Home
    val HomeFilled = Icons.Filled.Home
    val Search = Icons.Filled.Search
    val SettingsOutline = Icons.Outlined.Settings
    val SettingsFilled = Icons.Filled.Settings
    val BackArrow = Icons.Filled.ArrowBack
    val SaveAlt = Icons.Outlined.SaveAlt
    val Person = Icons.Filled.Person
}



sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}