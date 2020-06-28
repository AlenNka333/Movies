package com.hfad.movies.json

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
open class Movie(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName("popularity")
    val popularity: String,
    @SerializedName("vote_count")
    val vote_count: String,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("title")
    val title: String,
    @SerializedName("original_title")
    val original_title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("vote_average")
    val vote_average: String
)