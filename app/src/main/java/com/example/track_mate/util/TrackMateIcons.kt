package com.example.track_mate.util

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.track_mate.R.drawable as AppIcon

object TrackMateIcons {
    val HomeOutline = AppIcon.outline_home_24
    val HomeFilled = AppIcon.baseline_home_24
    val SearchOutline = AppIcon.baseline_search_24
    val SettingsOutline = AppIcon.outline_settings_24
    val SettingsFilled = AppIcon.baseline_settings_24
}



sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}