package com.example.flixter

import com.google.gson.annotations.SerializedName

class LatestMovie {
    @SerializedName("poster_path")
    var poster_path: String? = null

    @SerializedName("original_title")
    var original_title: String? = null

    @SerializedName("overview")
    var overview: String? = null

//    @SerializedName("title")
//    var title: String? = null

    @SerializedName("backdrop_path")
    var backdrop_path: String? = null
}

