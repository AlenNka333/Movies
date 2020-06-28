package com.hfad.movies.json

import com.google.gson.annotations.SerializedName

data class HomeFeed(
    @SerializedName("results")
    val result: List<Movie>
) {
}