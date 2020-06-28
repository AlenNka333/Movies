package com.hfad.movies.json

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String
)