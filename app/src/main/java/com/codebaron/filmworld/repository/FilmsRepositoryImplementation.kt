package com.codebaron.filmworld.repository

import com.codebaron.filmworld.models.Result
import com.codebaron.filmworld.services.EndPointProvider
import javax.inject.Inject

class FilmsRepositoryImplementation @Inject constructor(private val endPointProvider: EndPointProvider): FilmsRepository {

    private var films: List<Result>? = emptyList()

    override suspend fun getPopularFilms(
        apiKey: String,
        language: String,
        page: String
    ): List<Result>? {
        val apiResponse = endPointProvider.getPopularMovies(apiKey, language, page)
        if (apiResponse.isSuccessful) {
            val filmsData: List<Result>? = apiResponse.body()?.results
            films = filmsData ?: emptyList()
        }
        return films
    }

    override suspend fun getTrendingFilms(apiKey: String): List<Result>? {
        val apiResponse = endPointProvider.getTrendingMovies(apiKey)
        if (apiResponse.isSuccessful) {
            val filmsData: List<Result>? = apiResponse.body()?.results
            films = filmsData ?: emptyList()
        }
        return films
    }

    override suspend fun getTopRatedFilms(
        apiKey: String,
        language: String,
        page: String
    ): List<Result>? {
        val apiResponse = endPointProvider.getTopRatedMovies(apiKey, language, page)
        if (apiResponse.isSuccessful) {
            val filmsData: List<Result>? = apiResponse.body()?.results
            films = filmsData ?: emptyList()
        }
        return films
    }
}