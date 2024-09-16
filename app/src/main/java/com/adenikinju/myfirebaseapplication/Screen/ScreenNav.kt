package com.adenikinju.myfirebaseapplication.Screen

import com.adenikinju.myfirebaseapplication.R

sealed class Screen(val route: String, val title: String?, val icon: Int?) {
    object Movies : Screen("home", "Home", icon = R.drawable.baseline_movie_24)
    object Shows : Screen("shows", "shows", icon = R.drawable.baseline_slideshow_24)
    object Popular : Screen("popular", "popular", icon = R.drawable.baseline_recommend_24)
    object Register : Screen("register")
    object Login : Screen("main")

    constructor(route: String) : this(route = route, null, null)
}