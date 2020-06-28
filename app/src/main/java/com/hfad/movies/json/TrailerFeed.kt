package com.hfad.movies.json

import com.google.gson.annotations.SerializedName

class TrailerFeed(
    @SerializedName("results")
    val result: List<Trailer>
)