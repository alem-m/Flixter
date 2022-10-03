package com.example.flixter

import android.support.annotation.Keep
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SearchAPIResponse(
    @SerialName("genre")
    val genre: List<Genre>?,
) : java.io.Serializable

@Keep
@Serializable
data class Genre(
    @SerialName("name")
    val genre_name : String
) : java.io.Serializable

class LatestMovie(
    @SerialName("original_title")
    var original_title: String?,
    @SerialName("overview")
    var overview: String?,
    @SerialName("genre")
    var genre: Genre?,
    @SerialName("popularity")
    var popularity: String?,
    @SerialName("poster_path")
    var poster_path: PosterPath?,
    ) : java.io.Serializable{
    val movie_image_url = "https://image.tmdb.org/t/p/w500/$poster_path"

}

@Keep
@Serializable
data class PosterPath(
    @SerialName("poster_path")
    val poster_path : String?
) : java.io.Serializable

