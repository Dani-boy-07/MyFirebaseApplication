package com.adenikinju.myfirebaseapplication.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.adenikinju.myfirebaseapplication.data.models.MovieModel

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getMovies() : List<MovieModel>

    @Query("SELECT title FROM movies WHERE id = (:movieId)")
    fun getMovie(movieId: Int) : MovieModel

    @Insert
    fun insertMovies(vararg movies: movies)
}