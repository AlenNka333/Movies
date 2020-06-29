package com.hfad.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.movies.model.FavouriteMovie
import com.hfad.movies.model.Movie

@Database(entities = [Movie::class, FavouriteMovie::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    companion object {
        val DB_NAME = "movies1.db"
        var database: MovieDatabase? = null
        val LOCK = Object()

        fun getInstance(context: Context): MovieDatabase?{

            if(database ==null) {
                synchronized(LOCK) {
                    database = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return database
        }

    }

    abstract fun movieDao(): MovieDao

}