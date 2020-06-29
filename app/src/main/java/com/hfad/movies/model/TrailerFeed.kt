package com.hfad.movies.model

import com.google.gson.annotations.SerializedName
import com.hfad.movies.model.Trailer

class TrailerFeed(
    @SerializedName("results")
    val result: List<Trailer>
)