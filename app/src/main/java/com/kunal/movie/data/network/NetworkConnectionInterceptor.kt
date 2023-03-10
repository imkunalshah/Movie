package com.kunal.movie.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.kunal.movie.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor for checking the
 * status of internet connection.
 */
class NetworkConnectionInterceptor(
    context: Context
): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoInternetException("Network Unavailable")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable():Boolean{
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}