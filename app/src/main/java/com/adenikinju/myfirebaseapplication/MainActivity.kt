package com.adenikinju.myfirebaseapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adenikinju.myfirebaseapplication.Screen.Screen
import com.adenikinju.myfirebaseapplication.components.BottomNavBar
import com.adenikinju.myfirebaseapplication.components.Form
import com.adenikinju.myfirebaseapplication.components.Navigation
import com.adenikinju.myfirebaseapplication.ui.theme.MyFirebaseApplicationTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.lang.RuntimeException


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var analytics: FirebaseAnalytics
    private val activity = this
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics
        analytics.logEvent(
            FirebaseAnalytics.Event.SELECT_CONTENT, bundleOf(
                Pair(FirebaseAnalytics.Param.ITEM_ID, "0"),
                Pair(FirebaseAnalytics.Param.ITEM_NAME, "Start"),
                Pair(FirebaseAnalytics.Param.ITEM_BRAND, "App"),
                Pair(FirebaseAnalytics.Param.CONTENT_TYPE, "Launched"),
            )
        )
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        setContent {
            MyFirebaseApplicationTheme {
                navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
                                Screen.Movies.route,
                                Screen.Shows.route,
                                Screen.Popular.route,
                            )) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Navigation(
                        context = this,
                        modifier = Modifier.padding(innerPadding),
                        auth = auth
                    )
                }
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MyFirebaseApplicationTheme {
//        Greeting("Android", analytics = ana)
        }
    }


}

