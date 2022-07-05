package com.codebaron.filmworld.models.filmdetails

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)

val dummyBelongsToCollection = BelongsToCollection(
    backdrop_path = "/AvnqpRwlEaYNVL6wzC4RN94EdSd.jpg",
    id = 531241,
    name = "Spider-Man (Avengers) Collection",
    poster_path = "/nogV4th2P5QWYvQIMiWHj4CFLU9.jpg"
)
