package com.codebaron.filmworld.repository

import com.codebaron.filmworld.models.filmcredits.FilmCasts
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmsdata.Result

interface FilmsRepository {
    suspend fun getPopularFilms(apiKey: String, language: String, page: String): List<Result>?
    suspend fun getTrendingFilms(apiKey: String): List<Result>?
    suspend fun getTopRatedFilms(apiKey: String, language: String, page: String): List<Result>?
    suspend fun getFilmDetails(apiKey: String, language: String, movieId: String): FilmDetailsData?
    suspend fun getFilmCredits(apiKey: String, language: String, movieId: String): FilmCasts?
    suspend fun getSimilarFilms(apiKey: String, language: String, movieId: String): List<Result>?
}