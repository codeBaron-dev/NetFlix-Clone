package com.codebaron.filmworld.services

import com.codebaron.filmworld.models.TrendingMovies
import com.codebaron.filmworld.utils.POPULAR_MOVIES_API
import com.codebaron.filmworld.utils.TOP_RATED_MOVIES_API
import com.codebaron.filmworld.utils.TRENDING_MOVIES_API
import retrofit2.Response
import retrofit2.http.GET
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
        @Query("api_key", encoded = true) apiKey: String
    ): Response<TrendingMovies>

    @GET(TOP_RATED_MOVIES_API)
    suspend fun getTopRatedMovies(
        @Query("api_key", encoded = true) apiKey: String,
        @Query("language", encoded = true) language: String,
        @Query("page", encoded = true) page: String
    ): Response<TrendingMovies>
}