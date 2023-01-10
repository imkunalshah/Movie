package com.kunal.movie.data.network.apis

import com.kunal.movie.data.models.MovieCreditsDataResponse
import com.kunal.movie.data.models.MovieDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * End-Point Interface for calling
 * different endpoints in the API
 * (With Same Base URL)
 */
interface TMDbApi {

    @GET("movie/{movieId}")
    suspend fun getMovieData(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = "0799fb14a63d4ffe0ff4496b6bd30cfe",
        @Query("language") language: String = "en-US"
    ): Response<MovieDataResponse>

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCreditsData(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String = "0799fb14a63d4ffe0ff4496b6bd30cfe",
        @Query("language") language: String = "en-US"
    ): Response<MovieCreditsDataResponse>

}