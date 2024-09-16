package com.adenikinju.myfirebaseapplication.data.models

data class MoviesModel(
    val page : Int,
    val results : List<MovieModel>,
    val total_pages : Int,
    val total_results : Int,
    )
