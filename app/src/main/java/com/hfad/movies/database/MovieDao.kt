package com.hfad.movies.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hfad.movies.json.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM favourite_movies")
    fun getAllFavouriteMovies(): LiveData<List<FavouriteMovie>>

    @Query("SELECT * FROM movies WHERE id==:movieId")
    fun getMovie(movieId: Int): Movie?

    @Query("SELECT * FROM favourite_movies WHERE id==:movieId")
    fun getFavouriteMovie(movieId: Int): FavouriteMovie?

    @Update
    fun updateDB(movie: Movie)

    @Insert
    fun insertMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Insert
    fun insertFavouriteMovie(movie: FavouriteMovie)

    @Delete
    fun deleteFavouriteMovie(movie: FavouriteMovie)

    @Query("DELETE FROM favourite_movies")
    fun deleteAllFavouriteData()

    @Query("DELETE FROM movies")
    fun deleteAllData()
}