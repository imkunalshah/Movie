package com.kunal.movie.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieCreditsDataResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>
) : Parcelable {

    @Parcelize
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("gender")
        val gender: Long,
        @SerializedName("id")
        val id: Long,
        @SerializedName("known_for_department")
        val knownForDepartment: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("profile_path")
        val profilePath: String,
        @SerializedName("cast_id")
        val castId: Long,
        @SerializedName("character")
        val character: String,
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("order")
        val order: Long
    ) : Parcelable

    @Parcelize
    data class Crew(
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("gender")
        val gender: Long,
        @SerializedName("id")
        val id: Long,
        @SerializedName("known_for_department")
        val knownForDepartment: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("original_name")
        val originalName: String,
        @SerializedName("popularity")
        val popularity: Double,
        @SerializedName("profile_path")
        val profilePath: String,
        @SerializedName("credit_id")
        val creditId: String,
        @SerializedName("department")
        val department: String,
        @SerializedName("job")
        val job: String
    ) : Parcelable
}