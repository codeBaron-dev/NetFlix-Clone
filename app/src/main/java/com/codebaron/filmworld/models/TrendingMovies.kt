package com.codebaron.filmworld.models

data class TrendingMovies(
    val page: Int? = null,
    val results: List<Result>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)