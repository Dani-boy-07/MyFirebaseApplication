package com.adenikinju.myfirebaseapplication.data

import com.adenikinju.myfirebaseapplication.data.models.MoviesModel
import retrofit2.http.GET

interface ApiInterface {
    @GET("movie")
    suspend fun getMovies() : MoviesModel


}