package com.codebaron.filmworld.utils

import androidx.compose.ui.graphics.Color
import com.codebaron.filmworld.R
import com.codebaron.filmworld.navigation.BottomNavigationItems.*

const val DOT = "."
const val ADD_IMAGE_DESCRIPTION = "Add To List"
const val MY_LIST = "My List"
const val PLAY_BUTTON_DESCRIPTION = "Play Video"
const val PLAY = "Play"
const val MOVIE_INFO = "Movie Information"
const val INFO = "Info"
const val CONTINUE_WATCHING = "Continue Watching for Nicholas"
const val MORE_OPTION = "More"
const val TRENDING_NOW = "Trending Now"
const val SEARCH = "Search"
const val TOP_RATED = "Top Rated"
const val EPISODES_INFO = "Episode & Info"
const val POPULAR_NOW = "Popular Now"
const val INTERNET_ERROR_TITLE = "Internet Connection Error"
const val INTERNET_ERROR_MESSAGE = "No internet connection, currently displaying old films data, reconnect to internet to get updated contents"
const val POSITIVE_BUTTON_TEXT = "Confirm"
const val NEGATIVE_BUTTON_TEXT = "Dismiss"
const val LANGUAGE_TYPE = "en-US"
const val PAGE_PARAM = "1"
const val TABLE_NAME = "films_table"
const val UNKNOWN_ERROR = "An unknown error occurred"
const val INVALID_ACTION = "Current unable to play video at the moment"


//
const val TV_SHOWS = "TV Shows"
const val MOVIES = "Movies"
const val HOT_NEW = "Mew & Hot"
const val EDIT = "Edit"
const val VIEWER = "Who's Watching?"
const val NAME = "codeBaron"
const val KIDS = "Kids"
const val ADD_PROFILE = "Add Profile"

val SEASONS = listOf("S1", "S2", "S3", "S4", "S5")
val EPISODES = listOf("E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10")
val PROGRESS_LEVELS = listOf(0.0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f)
val IMAGES = listOf(R.drawable.anime, R.drawable.dummyimage, R.drawable.netflix)
val RANDOM_COLORS = listOf(Color.Red, Color.Green, Color.Yellow, Color.Blue)
val MEDIA_TYPE = listOf("all", "movie", "person", "tv")
val TIME_WINDOW = listOf("day", "week")
val MOVIE_WATCH_TIME = listOf("45min", "1hour", "53min", "1hour 2min", "30min", "44min")


val NAVIGATION_OBJECTS = listOf(
    Home,
    Games,
    NewHot,
    Downloads
)

const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/"
const val IMAGE_PATH_URL = "https://image.tmdb.org/t/p/w500"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POPULAR_MOVIES_API = "movie/popular"
const val API_KEY = "cbb2c4d1c8fdae3b9bbbef9250fdd002"
const val TRENDING_MOVIES_API = "trending/{media_type}/{time_window}"
const val TOP_RATED_MOVIES_API = "movie/top_rated"
const val MOVIE_DETAILS = "movie/{movie_id}"
const val MOVIE_CREDITS = "/movie/{movie_id}/credits"
const val SIMILAR_MOVIES = "movie/{movie_id}/similar"