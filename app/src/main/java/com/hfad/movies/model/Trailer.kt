package com.hfad.movies.model

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("key")
    val key: String,
    @SerializedName("name")
    val name: String
)