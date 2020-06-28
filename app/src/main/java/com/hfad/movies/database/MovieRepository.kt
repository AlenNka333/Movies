package com.hfad.movies.database

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.hfad.movies.json.Movie

class MovieRepository(application: Application) {

    private var movieDao: MovieDao
    private var allMovies: LiveData<List<Movie>>
    private var allFavouriteMovies: LiveData<List<FavouriteMovie>>
    init {
        val database = MovieDatabase.getInstance(
            application.applicationContext
        )
        movieDao = database!!.movieDao()
        allMovies = movieDao.getAllMovies()
    }
    init {
        val databaseF = MovieDatabase.getInstance(
            application.applicationContext
        )
        movieDao = databaseF!!.movieDao()
        allFavouriteMovies = movieDao.getAllFavouriteMovies()
    }

    fun insert(movie: Movie){
        val insertMovieAsyncTask = InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun update(movie: Movie){
        val updateMovieAsyncTask = UpdateMovieAsyncTask(movieDao).execute(movie)
    }

    fun deleteMovie(movie: Movie){
        val deleteMovieAsyncTask = DeleteMovieAsyncTask(movieDao).execute(movie)
    }

    fun getMovie(id: Int): Movie{
        return GetMovieAsyncTask(movieDao).execute(id).get()
    }

    fun deleteAllMovies(){
        val deleteAllAsyncTask = DeleteAllAsyncTask(movieDao).execute()
    }

    fun getAllMovies(): LiveData<List<Movie>> = allMovies

    /////////////////////////////////////////////////////////////////////

    fun insertFavouriteMovie(favouriteMovie: FavouriteMovie){
        val insertFavouriteMovie = InsertFavouriteMovie(movieDao).execute(favouriteMovie)
    }

    fun deleteFavouriteMovie(movie: FavouriteMovie){
        val deleteMovieAsyncTask = DeleteFavouriteMovieAsyncTask(movieDao).execute(movie)
    }

    fun getFavouriteMovie(id: Int): FavouriteMovie?{
        return GetFavouriteMovieAsyncTask(movieDao).execute(id).get()
    }

    fun getAllFavouritesMovies(): LiveData<List<FavouriteMovie>> = allFavouriteMovies

    fun deleteAllFavouritesMovies(){
        val deleteAllAsyncTask = DeleteAllFavouriteAsyncTask(movieDao).execute()
    }


    inner class InsertMovieAsyncTask(movieDao: MovieDao): AsyncTask<Movie, Unit, Unit>(){
        override fun doInBackground(vararg params: Movie?) {
            movieDao.insertMovie(params[0]!!)
        }

    }

    inner class InsertFavouriteMovie(movieDao: MovieDao): AsyncTask<FavouriteMovie, Unit, Unit>(){
        override fun doInBackground(vararg params: FavouriteMovie?) {
            movieDao.insertFavouriteMovie(params[0]!!)
        }

    }

    inner class UpdateMovieAsyncTask(movieDao: MovieDao): AsyncTask<Movie, Unit, Unit>(){
        override fun doInBackground(vararg params: Movie?) {
            movieDao.updateDB(params[0]!!)
        }

    }

    inner class DeleteMovieAsyncTask(movieDao: MovieDao): AsyncTask<Movie, Unit, Unit>(){
        override fun doInBackground(vararg params: Movie?) {
            movieDao.deleteMovie(params[0]!!)
        }

    }

    inner class DeleteFavouriteMovieAsyncTask(movieDao: MovieDao): AsyncTask<FavouriteMovie, Unit, Unit>(){
        override fun doInBackground(vararg params: FavouriteMovie?) {
            movieDao.deleteFavouriteMovie(params[0]!!)
        }

    }

    inner class GetMovieAsyncTask(movieDao: MovieDao): AsyncTask<Int, Unit, Movie>(){
        override fun doInBackground(vararg params: Int?):Movie? {
            return movieDao.getMovie(params[0]!!)
        }

    }

    inner class GetFavouriteMovieAsyncTask(movieDao: MovieDao): AsyncTask<Int, Unit, FavouriteMovie>(){
        override fun doInBackground(vararg params: Int?):FavouriteMovie? {
            return movieDao.getFavouriteMovie(params[0]!!)
        }

    }

    inner class DeleteAllFavouriteAsyncTask(movieDao: MovieDao): AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg params: Unit?) {
            movieDao.deleteAllFavouriteData()
        }

    }

    inner class DeleteAllAsyncTask(movieDao: MovieDao): AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg params: Unit?) {
            movieDao.deleteAllData()
        }

    }

}
