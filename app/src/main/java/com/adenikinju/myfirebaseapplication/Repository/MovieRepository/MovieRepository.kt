package com.adenikinju.myfirebaseapplication.Repository.MovieRepository

import com.adenikinju.myfirebaseapplication.data.models.MovieModel


interface MovieRepository {
    suspend fun getMovies() : List<MovieModel>
}