package com.codebaron.filmworld.models.filmcredits

data class FilmCasts(
    val cast: List<Cast>? = null,
    val crew: List<Crew>? = null,
    val id: Int? = null
)

val dummyFilmCasts = FilmCasts(
    cast = dummyCast,
    crew = dummyCrew,
    id = 20
)