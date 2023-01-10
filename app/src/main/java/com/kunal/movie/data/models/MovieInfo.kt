package com.kunal.movie.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieInfo(
    val movieDataResponse: MovieDataResponse,
    val movieCreditsDataResponse: MovieCreditsDataResponse
) : Parcelable
