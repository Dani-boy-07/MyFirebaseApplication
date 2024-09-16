package com.adenikinju.myfirebaseapplication.data.models

data class MovieModel(
    val adult : Boolean,
    val backdrop_path: String,
    val id : Int,
    val original_language: String,
    val original_title : String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)
