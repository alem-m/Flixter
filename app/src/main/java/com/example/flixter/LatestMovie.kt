package com.example.flixter

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Result(
    @SerialName("results")
    val results: List<LatestMovie>?
)

@Keep
@Serializable
data class LatestMovie(
    @SerialName("original_title")
    val title: String?,
    @SerialName("overview")
    val overview: String?,
    @SerialName("popularity")
    val popularity: String?,
    @SerialName("poster_path")
    val poster_path: String?,
    ) : java.io.Serializable{
    val movie_image_url = "https://image.tmdb.org/t/p/w500/${PosterPath}"

}

@Keep
@Serializable
data class PosterPath(
    @SerialName("poster_path")
    val poster_path : String?
) : java.io.Serializable

