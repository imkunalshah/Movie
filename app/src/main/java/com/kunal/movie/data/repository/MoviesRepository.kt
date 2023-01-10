package com.kunal.movie.data.repository

import com.kunal.movie.data.network.apis.TMDbApi
import com.kunal.movie.utils.safeApiCall
import kotlinx.coroutines.flow.flow

/**
 * Data layer for calling APIs,
 * performing CRUD operations
 * From the UI via ViewModel
 */
class MoviesRepository(
    private val tmDbApi: TMDbApi
) {

    suspend fun getMovieData(movieId: String) = flow {
        emit(
            safeApiCall { tmDbApi.getMovieData(movieId = movieId) }
        )
    }

    suspend fun getMovieCreditsData(movieId: String) = flow {
        emit(
            safeApiCall { tmDbApi.getMovieCreditsData(movieId = movieId) }
        )
    }

}