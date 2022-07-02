package com.codebaron.filmworld.navigation

import com.codebaron.filmworld.R

sealed class BottomNavigationItems(
    var title: String,
    var icon: Int,
    var destinations: String
) {
    object Home : BottomNavigationItems(
        "Home",
        R.drawable.ic_outline_home_24,
        Destinations.MOVIES_HOME_SCREEN.name
    )

    object Games : BottomNavigationItems(
        "Games",
        R.drawable.ic_outline_videogame_asset_24,
        Destinations.GAMES_SCREEN.name
    )

    object NewHot : BottomNavigationItems(
        "New & Hot",
        R.drawable.ic_baseline_video_library_24,
        Destinations.HOT_NEW_SCREEN.name
    )

    object Downloads : BottomNavigationItems(
        "Downloads",
        R.drawable.ic_outline_arrow_circle_down_24,
        Destinations.DOWNLOADS_SCREEN.name
    )
}
