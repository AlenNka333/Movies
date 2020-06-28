package com.hfad.movies.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hfad.movies.json.Movie

class MovieViewModel(application: Application): AndroidViewModel(application) {

    val repository = MovieRepository(application)
    val allData: LiveData<List<Movie>> = repository.getAllMovies()
    val allFavouriteData: LiveData<List<FavouriteMovie>> = repository.getAllFavouritesMovies()

    fun insert(movie: Movie) = repository.insert(movie)

    fun insertFavouriteMovie(movie: FavouriteMovie) = repository.insertFavouriteMovie(movie)

    fun update(movie: Movie) = repository.update(movie)

    fun getMovie(id: Int) = repository.getMovie(id)

    fun getFavouriteMovie(id: Int) = repository.getFavouriteMovie(id)

    fun getAll() = allData

    fun getFavouriteAll() = allFavouriteData

    fun deleteMovie(movie: Movie) = repository.deleteMovie(movie)

    fun deleteFavouriteMovie(movie: FavouriteMovie) = repository.deleteFavouriteMovie(movie)

    fun deleteAll() = repository.deleteAllMovies()

    fun deleteAllFavourites() = repository.deleteAllFavouritesMovies()


}