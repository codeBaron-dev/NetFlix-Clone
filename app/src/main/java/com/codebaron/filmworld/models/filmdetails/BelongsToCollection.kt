package com.codebaron.filmworld.models.filmdetails

data class BelongsToCollection(
    val backdrop_path: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val poster_path: String? = null
)

val dummyBelongsToCollection = BelongsToCollection(
    backdrop_path = "/AvnqpRwlEaYNVL6wzC4RN94EdSd.jpg",
    id = 531241,
    name = "Spider-Man (Avengers) Collection",
    poster_path = "/nogV4th2P5QWYvQIMiWHj4CFLU9.jpg"
)
