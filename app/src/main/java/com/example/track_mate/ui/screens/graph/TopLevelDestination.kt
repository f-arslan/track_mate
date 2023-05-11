package com.example.track_mate.ui.screens.graph

import com.example.track_mate.util.Icon
import com.example.track_mate.util.Icon.DrawableResourceIcon
import com.example.track_mate.util.TrackMateIcons
import com.example.track_mate.R.string as AppText


enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = DrawableResourceIcon(TrackMateIcons.HomeFilled),
        unselectedIcon = DrawableResourceIcon(TrackMateIcons.HomeOutline),
        iconTextId = AppText.home,
        titleTextId = AppText.home
    ),
    SEARCH(
        selectedIcon = DrawableResourceIcon(TrackMateIcons.SearchOutline),
        unselectedIcon = DrawableResourceIcon(TrackMateIcons.SearchOutline),
        iconTextId = AppText.search,
        titleTextId = AppText.search
    ),
    SETTINGS(
        selectedIcon = DrawableResourceIcon(TrackMateIcons.SettingsFilled),
        unselectedIcon = DrawableResourceIcon(TrackMateIcons.SettingsOutline),
        iconTextId = AppText.settings,
        titleTextId = AppText.settings
    )
}