package com.adenikinju.myfirebaseapplication.Repository.MovieRepository

import com.adenikinju.myfirebaseapplication.data.ApiInterface
import com.adenikinju.myfirebaseapplication.data.models.MovieModel
import com.adenikinju.myfirebaseapplication.data.models.MoviesModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : MovieRepository {
    override suspend fun getMovies(): List<MovieModel> {
        return apiInterface.getMovies().results
    }
}