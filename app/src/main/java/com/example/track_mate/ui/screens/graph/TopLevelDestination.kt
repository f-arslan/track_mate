package com.example.track_mate.ui.screens.graph

import com.example.track_mate.util.Icon
import com.example.track_mate.util.Icon.ImageVectorIcon
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = ImageVectorIcon(TrackMateIcons.HomeFilled),
        unselectedIcon = ImageVectorIcon(TrackMateIcons.HomeOutline),
        iconTextId = AppText.home,
        titleTextId = AppText.home
    ),
    SEARCH(
        selectedIcon = ImageVectorIcon(TrackMateIcons.Search),
        unselectedIcon = ImageVectorIcon(TrackMateIcons.Search),
        iconTextId = AppText.search,
        titleTextId = AppText.search
    ),
    SETTINGS(
        selectedIcon = ImageVectorIcon(TrackMateIcons.SettingsFilled),
        unselectedIcon = ImageVectorIcon(TrackMateIcons.SettingsOutline),
        iconTextId = AppText.settings,
        titleTextId = AppText.settings
    )
}