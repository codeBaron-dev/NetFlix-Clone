package com.codebaron.filmworld.repository

import com.codebaron.filmworld.models.filmsdata.Result

interface FilmsRepository {
    suspend fun getPopularFilms(apiKey: String, language: String, page: String): List<Result>?
    suspend fun getTrendingFilms(apiKey: String): List<Result>?
    suspend fun getTopRatedFilms(apiKey: String, language: String, page: String): List<Result>?
}