package com.codebaron.filmworld.services

import com.codebaron.filmworld.models.filmcredits.FilmCasts
import com.codebaron.filmworld.models.filmdetails.FilmDetailsData
import com.codebaron.filmworld.models.filmsdata.TrendingMovies
import com.codebaron.filmworld.utils.MOVIE_DETAILS
import com.codebaron.filmworld.utils.POPULAR_MOVIES_API
import com.codebaron.filmworld.utils.TOP_RATED_MOVIES_API
import com.codebaron.filmworld.utils.TRENDING_MOVIES_API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointProvider {

    @GET(POPULAR_MOVIES_API)
    suspend fun getPopularMovies(
        @Query("api_key", encoded = true) apiKey: String,
        @Query("language", encoded = true) language: String,
        @Query("page", encoded = true) page: String
    ): Response<TrendingMovies>

    @GET(TRENDING_MOVIES_API)
    suspend fun getTrendingMovies(
        @Path("media_type", encoded = true) mediaType: String,
        @Path("time_window", encoded = true) timeWindow: String,
        @Query("api_key", encoded = true) apiKey: String,
    ): Response<TrendingMovies>

    @GET(TOP_RATED_MOVIES_API)
    suspend fun getTopRatedMovies(
        @Query("api_key", encoded = true) apiKey: String,
        @Query("language", encoded = true) language: String,
        @Query("page", encoded = true) page: String
    ): Response<TrendingMovies>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("api_key", encoded = true) apiKey: String,
        @Query("language", encoded = true) language: String
    ): Response<FilmDetailsData>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieCredits(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("api_key", encoded = true) apiKey: String,
        @Query("language", encoded = true) language: String
    ): Response<FilmCasts>
}