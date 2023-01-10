package com.kunal.movie.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

/**
 * Function for handling
 * safe API requests and processing the response/error
 * through a generic resource type
 */
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                Resource(ApiStatus.SUCCESS, response.body(), null)
            } else {
                Resource(ApiStatus.ERROR, null, response.errorBody()?.string())
            }
        } catch (e: Exception) {
            Resource(ApiStatus.ERROR, null, e.message)
        }
    }
}