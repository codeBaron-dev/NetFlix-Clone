package com.codebaron.filmworld.repository

import com.codebaron.filmworld.models.filmcredits.FilmCasts
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmsdata.Result
import com.codebaron.filmworld.services.EndPointProvider
import javax.inject.Inject

class FilmsRepositoryImplementation @Inject constructor(private val endPointProvider: EndPointProvider) :
    FilmsRepository {

    private var films: List<Result>? = emptyList()
    private var filmDetails: FilmDetailsData? = null
    private var filmCasts: FilmCasts? = null

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
        val apiResponse =
            endPointProvider.getTrendingMovies("all", "week", apiKey)
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

    override suspend fun getFilmDetails(
        apiKey: String,
        language: String,
        movieId: String
    ): FilmDetailsData? {
        val apiResponse = endPointProvider.getMovieDetails(movieId, apiKey, language)
        if (apiResponse.isSuccessful) {
            val filmData: FilmDetailsData? = apiResponse.body()
            filmDetails = filmData
        }
        return filmDetails
    }

    override suspend fun getFilmCredits(
        apiKey: String,
        language: String,
        movieId: String
    ): FilmCasts? {
        val apiResponse = endPointProvider.getMovieCredits(movieId, apiKey, language)
        if (apiResponse.isSuccessful) {
            val filmCredits: FilmCasts? = apiResponse.body()
            filmCasts = filmCredits
        }
        return filmCasts
    }

    override suspend fun getSimilarFilms(
        apiKey: String,
        language: String,
        movieId: String
    ): List<Result>? {
        val apiResponse = endPointProvider.getSimilarMovies(movieId, apiKey, language)
        if (apiResponse.isSuccessful) {
            val similarMovies: List<Result>? = apiResponse.body()?.results
            films = similarMovies
        }
        return films
    }
}