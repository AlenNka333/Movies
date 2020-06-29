package com.hfad.movies.model

import com.google.gson.annotations.SerializedName
import com.hfad.movies.model.Movie

data class HomeFeed(
    @SerializedName("results")
    val result: List<Movie>
) {
}