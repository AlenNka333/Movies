package com.hfad.movies.model

import androidx.room.Entity
import androidx.room.Ignore
import com.hfad.movies.model.Movie

@Entity(tableName = "favourite_movies")
class FavouriteMovie : Movie {
    constructor(
        id: Int,
        popularity: String,
        vote_count: String,
        poster_path: String?,
        backdrop_path: String?,
        title: String,
        original_title: String,
        overview: String,
        release_date: String,
        vote_average: String
    ) : super(
        id,
        popularity,
        vote_count,
        poster_path,
        backdrop_path,
        title,
        original_title,
        overview,
        release_date,
        vote_average
    )

    @Ignore
    constructor(movie: Movie) :
            super(movie.id, movie.popularity, movie.vote_count,
                movie.poster_path, movie.backdrop_path,
                movie.title, movie.original_title,
                movie.overview, movie.release_date, movie.vote_average)

}

