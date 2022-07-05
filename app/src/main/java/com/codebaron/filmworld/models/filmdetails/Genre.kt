package com.codebaron.filmworld.models.filmdetails

data class Genre(
    val id: Int,
    val name: String
)

val dummyGenre = listOf(
    Genre(
        id = 230,
        name = "Action"
    ),
    Genre(
        id = 100,
        name = "Fiction"
    ),
    Genre(
        id = 12,
        name = "Adventure"
    )
)