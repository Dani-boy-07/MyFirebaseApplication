package com.adenikinju.myfirebaseapplication.ui.Screens.Shows

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.adenikinju.myfirebaseapplication.components.BottomNavBar
import com.adenikinju.myfirebaseapplication.components.Navigation
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ShowsScreen(navController: NavHostController) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        bottomBar = { BottomNavBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Add any other content here
            Text(
                text = "Welcome to the non Screen!",
                modifier = Modifier.padding(16.dp)
            )

        }

    }

}
