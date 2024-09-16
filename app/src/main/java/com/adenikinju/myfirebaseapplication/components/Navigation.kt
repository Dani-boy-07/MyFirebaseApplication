package com.adenikinju.myfirebaseapplication.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.adenikinju.myfirebaseapplication.ui.Screens.Home.HomeScreen
import com.adenikinju.myfirebaseapplication.ui.Screens.Login.LoginScreen
import com.adenikinju.myfirebaseapplication.ui.Screens.Popular.PopularScreen
import com.adenikinju.myfirebaseapplication.ui.Screens.Register.RegisterScreen
import com.adenikinju.myfirebaseapplication.ui.Screens.Shows.ShowsScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation (modifier: Modifier,context: Context, auth: FirebaseAuth){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main") {
        composable("main") { LoginScreen(modifier = Modifier, context = context, navController = navController, auth) }
        composable("register") { RegisterScreen(modifier = Modifier, context = context, navController) }
        composable("home") { HomeScreen(navController = navController, context = context) }
        composable("shows") { ShowsScreen(navController) }
        composable("popular") { PopularScreen(navController) }
    }
}